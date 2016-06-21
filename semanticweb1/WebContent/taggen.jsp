<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<link href="lib/css/nanoscroller.css" rel="stylesheet">
<link href="lib/css/emoji.css" rel="stylesheet">
<link href="general.css" rel="stylesheet">

<title>Auto Generate Tags</title>
</head>
<body>
	<h1>Use semantic web ontology to auto generate tags from emoji's</h1>
	<div id="descrip">This app takes your comment and use the emoji's in your
	 comment and an ontology in our server to generate tags</div>
	 <div id="main-container">
	 <form>
	 	Your comment here:
	 	<textarea id="text" data-emojiable="true" data-emoji-input="unicode" rows="5" cols="400">
	 	</textarea>
	 	<input type="button" id="send" value="Get Tags">
	 	<div id="tags" name="tags">
	 	</div>
	 	
	 </form>
	 </div>
  <script src="jquery-1.8.0.min.js"></script> 
  <script src="lib/js/nanoscroller.min.js"></script>
  <script src="lib/js/tether.min.js"></script>
  <script src="lib/js/config.js"></script>
  <script src="lib/js/util.js"></script>
  <script src="lib/js/jquery.emojiarea.js"></script>
  <script>
  	
  </script>
  <script src="lib/js/emoji-picker.js"></script>
  <script>
  		new EmojiPicker().discover();
  		
  		function getUnicodes(str){
  			str1="";
  			ind=0;
  			while(ind<str.length){
  				point=str.charCodeAt(ind);
  				if(point>=0xd800){
  					point1=str.charCodeAt(ind+1);
  					if(point1>=0xdc00){
  						unicode=(point-0xd800)*0x400+(point1-0xdc00)+0x10000;
  						str1+=unicode+":";
  						ind+=2;
  					}
  					else {
  						ind++;
  					}
  				}
  				else {
  					ind++;
  				}
  			}
  			return str1;
  		}
  		
  		function sendData(){
  			str=getUnicodes($("#text").val());
  			$.post("TagGeneratorServlet",{text:str},function(data,status){
  				//window.alert(status);
  				//window.alert(data);
  				$("#tags").text(data);
  			});
  		}
  		
  		$("#send").click(sendData);
  		
  		
  </script>
  
 
  
  

</body>
</html>