#!/usr/bin/perl -w

# packages
use strict;
use LWP;
use XML::XPath;
use List::MoreUtils qw(each_array);

my $baseUrl = 'http://localhost:8080/cliches2/';
my $ua = LWP::UserAgent->new;

runTests();

## Run CRUD tests against the service.
sub runTests {
    getTest($baseUrl);               ## GET all (xml)
    getTest($baseUrl . '?id=4');     ## GET one (xml)
    getTestJson($baseUrl);           ## GET all (json)
    getTestJson($baseUrl . '?id=4'); ## GET one (json)
    postTest($baseUrl);              ## POST
    getTest($baseUrl);               ## GET all (xml)
    putTest($baseUrl);               ## PUT
    getTest($baseUrl . '?id=4');     ## GET one (xml)
    deleteTest($baseUrl . '?id=31'); ## DELETE 
    getTest($baseUrl);               ## GET one (xml)
}

sub getTest {
    my ($url) = @_;

    print "\nGET request against $url\n\n";
    my $request = HTTP::Request->new(GET => $url);
    my $response = $ua->request($request);
    handleResponse($response, \&parseXml); # pointer to a function
}

sub getTestJson {
    my ($url) = @_;

    print "\nGET JSON request against $url\n\n";
    my $request = HTTP::Request->new(GET => $url, 
				     HTTP::Headers->new('Accept' => 'application/json'));
    my $response = $ua->request($request);
    handleResponse($response, \&parseJson);
}

sub postTest {
    my ($url) = @_;
    my $request = HTTP::Request->new(POST => $url);
    $request->content_type('application/x-www-form-urlencoded');
    $request->content('who=TS Eliot&what=This is the way the world ends.');
    my $response = $ua->request($request);
    handleResponse($response, undef);
}

sub putTest {
    my ($url) = @_;
    my $request = HTTP::Request->new(PUT => $url);
    $request->content_type('application/x-www-form-urlencoded');
    $request->content('id=4#who=FOOBAR');
    my $response = $ua->request($request);
    handleResponse($response, undef);
}

sub deleteTest {
    my ($url) = @_;
    my $request = HTTP::Request->new(DELETE => $url);
    my $response = $ua->request($request);
    handleResponse($response, undef);
}

sub parseXml {
    my ($rawXml) = @_;
    # print "Raw XML resonse:\n" . $rawXml . "\n";

    # Set up the XPath search.
    my $xp = XML::XPath->new(xml => trim($rawXml));
    
    # Extract a list apiece of ids, whos, and whats.
    my @ids = $xp->find('//object/void[@property="id"]/int')->get_nodelist;
    my @whos = $xp->find('//object/void[@property="who"]/string')->get_nodelist;
    my @whats = $xp->find('//object/void[@property="what"]/string')->get_nodelist;

    # Iterate over the arrays to print the data.
    my $it = each_array(@ids, @whos, @whats);
    while (my ($id, $who, $what) = $it->()) {
	print sprintf("%2d: ", $id->string_value) . 
	    $who->string_value . " -> '" . 
	    $what->string_value . "'\n";
    }
}

sub parseJson {
    my ($json) = @_;
    print "JSON document:\n$json\n";
    # ...
}

sub trim {
    my $string = shift;
    $string =~ s/^\s+//;
    $string =~ s/\s+$//;
    return $string;
}

sub handleResponse {
    my ($response, $callback) = @_;

    if ($response->is_success) {
	if (defined $callback) {
	    $callback->($response->content);
	}
	else {
	    print $response->content . "\n";
	}
    }
    else {
	print STDERR $response->status_line . "\n";
    }
}
