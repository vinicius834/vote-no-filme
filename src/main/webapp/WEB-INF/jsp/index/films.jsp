<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/assets/css/films.css'/>">
<script src="<c:url value="/assets/js/controllers/films.js"/>" type="text/javascript" charset="utf-8"></script>

<div id="div_content_filmvote" class="well">
	<div id="div_titulo_pergunta">
		<h3 class="brand">Qual filme você mais gosta?</h3>
	</div>
	<div id="div_content_films">
		<div id="div_films">
			<div id="div_film1" class="btn"></div>
			<div id="div_film2" class="btn"></div>
		</div>
	</div>
	<div id="div_info_person">
		<div id="div_titulo_info_person">
			<h3>Por favor, informe os dados abaixo para confirmar os votos</h3>
		</div>
		<div id="div_error_info_person"></div>
		<div id="div_txts_info_person">
				<div class="input-group">
					<input type="text" id="txt_person_name" class="input-xlarge" maxlength="60" name="name" placeholder="Seu Nome"/> 
					<input type="text" id="txt_person_email" class="input-xlarge" maxlength="60" name="email" placeholder="Email"/>
				</div>
		</div>
	</div>
	<div id="div_button_confirma">
		<input type="button" id="btn_confirma" class="btn" value="Confirma" />
	</div>
</div>
<div id="div_ranking" class="well">
	<div id="div_general_ranking"></div>
	<div id="div_person_ranking" ></div>
</div>
