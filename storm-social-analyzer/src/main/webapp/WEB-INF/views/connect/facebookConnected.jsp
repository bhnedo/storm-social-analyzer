<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script src="http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.4/underscore-min.js"></script>

<script>
	$(document).ready(function() {
		  
		function sortByValue(object) {
		    var tuples = _.map(object, function (value, key) { return [key, value]; });
		    return _.sortBy(tuples, function (tuple) { return tuple[1]; });
		}
		
		  $("#analyze").click(function(event) {
			  event.preventDefault();
			  $.ajax( { url: '/storm-social-analyzer/analyze', type: 'POST' });
		  });
		  
		  var wordsByCount = {};
		  var startDate = null;
		  var socket = new WebSocket("ws://localhost:9998");

				    socket.onmessage = function(event) {
				    	 
				    	 if ( startDate == null )
				    		  startDate = new Date();
				    	 $("#time").html(Math.abs(new Date() - startDate).toString());
				    	
				    	 var payload = JSON.parse(event.data);
				    	 _.each(payload, function(p) {
				    		  
				    		 wordsByCount[p.word] = p.count;
				    		  
				    	 });
				    	
				    	 sorted = sortByValue(wordsByCount).reverse();
				    	 sorted = sorted.slice(0, 20);
				    	
				    	 $("#results").empty();
				    	 _.each(sorted, function(e) {
				    		 $("#results").append("<li>" + e + " </li>");
				    	 });
				    	 
				    	 
				    }

				    socket.onopen = function(event) {
				        
				    };

				    socket.onclose = function(event) {
				       
				    };
	  });
	    

</script>


<button id="analyze" type="submit">Start analysis</button>

<div>Time elapsed (ms): <p id="time"></p></div>

<ol id="results">

</ol>



