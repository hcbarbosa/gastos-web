<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
</head>
<body>
<script>
function maskData(obj){
	var v = obj.value;
    if (v.match(/^\d{2}$/) !== null) {
        obj.value = v + '/';
    } else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
    	obj.value = v + '/';
    }
}
</script>
<form action="gastos" method="POST">

	Pesquisa gastos por mês.
	<br>
	Data inicial: 
	<input type="text" pattern="\d{1,2}/\d{1,2}/\d{4}" class="datepicker" name="data_ini" onkeyup="maskData(this)" maxlength="10"/>
	<br>
	Data final: 
	<input type="text" pattern="\d{1,2}/\d{1,2}/\d{4}" class="datepicker" name="data_fim" onkeyup="maskData(this)" maxlength="10"/>
	<br>
	<input type="submit" value="Gerar relatório" />

</form>
</body>
</html>