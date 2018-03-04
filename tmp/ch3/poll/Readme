1. Deploy in the usual way: ant -Dwar.name=ajax deploy
2. Open a browser to http://localhost:8080/ajax
3. If predictions3.war has been freshly deployed, the browswer should display 32 predictions.
4. To test, issue a curl or comparable request to change the underlying data, for instance:

      curl --request DELETE localhost:8080/predictions3/resourcesP/delete/32
5. In 5 seconds or so, the browser should update automatically with only 31 predictions.


