<html>
  <head>
<title>Data Holder</title>
<style type="text/css">
body{
    font-family: fantasy;
    font-size: 15px;
    color: #333;
}
</style>
<script type="text/javascript">
function search1(x){
 var x=(document.getElementsByName("hidden"));
 (x[0]).setAttribute("value", "search");
 
}
function edit1(x){
 var x=(document.getElementsByName("hidden"));
 (x[0]).setAttribute("value", "edit");
}
var btndown = function(ob){


try{
    ob.style["box-shadow"] = "1px 1px 5px #333";
}catch(e){}
try{
    ob.styel["-moz-box-shadow"] = "1px 1px 5px #333";
}catch(e){}
}
var btnup = function(ob){
try{
    ob.style["box-shadow"] = "3px 3px 10px #333";
}catch(e){}
try{
    ob.styel["-moz-box-shadow"] = "3px 3px 10px #333";
}catch(e){}
}
</script>
</head>
<body >
    <jsp:useBean  id="frontBean" class="Application.frontBean" scope="session" />
    <%
    int x=frontBean.getCount();
   if(x==0){
    x++;
    frontBean.setCount(x);
    frontBean.setNoProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:none");
    frontBean.setShowProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:none");
    }
    %>

<H1>Profile Manager</H1>
    <span style="position: absolute; top: 100px; left: 20px;" >Didn't signed up yet?</span>

    <span style="position: absolute; top: 100px; left: 200px;"><a href="datacollector.jsp">Sign up</a></span>

   

    <span style="position: absolute; top: 128px; left: 20px;">Search me:</span>
  
<form  action="Application1" method="post">
    <input type="text" name="sId" id="searchText" style="position: absolute; top: 145px; left: 20px; width: 200px; height: 30px; border-radius: 10px; font-size: 16px; padding: 2px;"/>
<input type="submit" onmouseover="search1(this)" value="search" name="search" style="position: absolute; top: 145px; left: 230px; box-shadow: 3px 3px 10px #333; padding: 4px 10px 4px 10px; border-radius: 5px; color: #a33; cursor: pointer; background-color:#fff;"/>


    <div id="viewer" name="noProf" style="${frontBean.noProf}"> NO PROFILE FOUND</div>
    <div id="dynamic_content" name="showProf" style="${frontBean.showProf}">

        <h3>Your details</h3>
        <table>
	 
	  <tr>
            <td>Name:</td><td>${frontBean.name}</td>
          </tr>
	  <tr>
            <td>Address:</td><td>${frontBean.add}</td>
          </tr>
	  <tr>
            <td>Phone:</td><td>${frontBean.phone}</td>
          </tr>
	  <tr>
            <td>Email:</td><td>${frontBean.email}</td>
          </tr>
	  <tr>
            <td>Discription:</td><td>${frontBean.desc}</td>
          </tr>
          <tr><td>

<input type="submit" value="Edit" onmouseover="edit1(this)" style="position:relative; top:20px;box-shadow: 3px 3px 10px #333; padding: 4px 10px 4px 10px; border-radius: 5px; color: #a33; cursor: pointer; background-color:#fff" />
</td><td></td></tr>
	</table>

       <input type="hidden" value="hey" name="hidden" />
    </div>
</form>
    <address>
        <a href="mailto:hms@hmspc10"></a>
    </address>
</body>
</html>
