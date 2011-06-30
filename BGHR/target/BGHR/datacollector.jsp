<html>
  <head>
<title>Get Data</title>
<style type="text/css">
body{
    font-family: fantasy;
    font-size: 18px;
    color: #333;
    background-color:#eee;
}
.in{
    position: absolute;
    top: 145px;
    left: 200px;
    width: 300px;
    height: 30px;
    border-radius: 10px;
    -moz-border-radius:5px;
    font-size: 16px;
    padding: 2px;
}
.intext{
    position: absolute;
    top: 100px;
    left: 100px;
}
.btn{
position: absolute;
top: 460px;
left: 230px;
box-shadow: 3px 3px 10px #333;
-moz-box-shadow: 3px 3px 10px #333;
padding: 4px 10px 4px 10px;
border-radius: 5px;
-moz-border-radius:5px;
color: #A33;
cursor: pointer;
-moz-border-radius:5px;
}
</style>
<script type="text/javascript">

function reset1(x){
 var x=(document.getElementsByName("name"));
 (x[0]).setAttribute("value", " ");
 var x1=(document.getElementsByName("add"));
 (x1[0]).setAttribute("value", " ");
 var x2=(document.getElementsByName("phone"));
 (x2[0]).setAttribute("value", " ");
 var x3=(document.getElementsByName("email"));
 (x3[0]).setAttribute("value", " ");
 var x4=(document.getElementsByName("desc"));
 (x4[0]).setAttribute("value", " ");


}




</script>
</head>
<body>
<h1><span id="heading">Get Data</span></h1>

<form action="Application2">
<span class="intext">ID: ${frontBean.id}</span>



<span id="ID" style="position: absolute; top: 100px; left: 200px;"></span>
<span class="intext" style="top: 150px;">Name:</span>
<input type="text"  id="nameField" name="name" value="${frontBean.name}" class="in" style="top: 145px;"/>
<span class="intext" style="top: 200px;">Address:</span>
<input type="text" id="addressField" name="add" value="${frontBean.add}"  class="in" style="top: 195px;"/>
<span class="intext" style="top: 250px;">Phone:</span>
<input type="text" id="phoneFeld" name="phone" value="${frontBean.phone}" class="in" style="top: 245px;"/>
<span class="intext" style="top: 300px;">email:</span>
<input type="text" id="emailField" name="email" value="${frontBean.email}" class="in" style="top: 295px;"/>
<span class="intext" style="top: 350px;">Discription:</span>
<textarea id="discField" name="desc" class="in" value="${frontBean.desc}" style="top: 345px; height: 100px;"></textarea>
<input type="submit" style="position:absolute;top:460px;left:230px; width: 80px; height: 40px; border-radius: 10px; background-color: #eee; box-shadow: 3px 3px 10px #333; color: #A33; cursor:pointer;"/>
<input type="reset" onclick="reset1(this)" style="position:absolute;top:460px;left:400PX; width: 80px; height: 40px; border-radius: 10px; background-color: #eee; box-shadow: 3px 3px 10px #333; color: #A33; cursor:pointer;" />
</form>
<a href="/frontpage.jsp"><input type="button" value="Back" style="position:absolute;top:560px;left:350px; width: 80px; height: 40px; border-radius: 10px; background-color: #eee; box-shadow: 3px 3px 10px #333; color: #A33; cursor:pointer;"/></a>
<address>
<a href="mailto:hms@hmspc10"></a>
</address>
</body>
</html>
