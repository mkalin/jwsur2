class Test {
    public static void main(String[ ] args) {
	String str = "{catalog:{cd:[foo, bar, baz]}}";
	int start = str.indexOf('[');
	int end = str.indexOf(']') + 1;
	System.out.println(str.substring(start, end));
    }
}
