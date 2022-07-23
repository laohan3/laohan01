<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>省市级联查询</title>
  <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
  <script type="text/javascript">//ajax-servlet-jdbc-html
    $(function (){
      //Province2Query的ajax请求
      //$("#button1").click(function () {
        $.ajax({
          url: "province2query",
          dataType: "json",
          success: function (resp) {
            $("#province2").empty();
            $.each(resp, function (i, n) {
              $("#province2").append("<option value='" + n.id + "'>" + n.name + "</option>")
            })


          }
        })

        //Cityquery的ajax请求
        $("#province2").change(function (){
            //获取选中状态的option的value
            var $obj=$("#province2>option:selected");
            var provinceid=$obj.val();
            $.get("cityquery",{provinceid:provinceid},function (resp){
                $("#city").empty();
                $.each(resp, function (i, n) {
                    $("#city").append("<option value='" + n.id + "'>" + n.name + "</option>")
                })
            },"json");
        })


     })

  </script>
</head>
<body>
    省份:<select id="province2">
           <option>请选择...</option>
        </select><br>

    城市:<select id="city">
          <option>请选择...</option>
        </select><br>
<input type="button" value="查看" id="button1">
</body>
</html>