$(document).ready(function() {
	var currentUrl = jQuery(location).attr('href');
	var filmsToVote = new Array();
	var votedFilms = new Array();
	var avalaibleFilms = new Array();
	var filmsRanking = new Array();
	$("#div_content_films").hide();
	$.getJSON(currentUrl + "getfilms", "", function(filmsList) {
		avalaibleFilms = filmsList.films;
		if (avalaibleFilms.length == 0) {
			alert("Error: filmes não cadastrados ou falha na busca");
			return;
		}
		for (var i = 0; i < avalaibleFilms.length - 1; i++) {
			for (var j = i + 1; j < avalaibleFilms.length; j++) {
				filmsToVote.push({films:{film1: avalaibleFilms[i], film2: avalaibleFilms[j]}});
			}	
		}
		showFilmsToCompare();
		$("#div_content_films").show("slow");
	});
	
	$("#btn_confirma").click(function() {
		var personEmail = $("#txt_person_email").val();
		var personName = $("#txt_person_name").val();
		var filtro = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		$("#div_error_info_person").empty();
		if(personName == null || personName === "") {
			$("#div_error_info_person").prepend("<div id='div_person_name_error' class='alert alert-danger'>Nome inválido!</div>");
            return;
        }
		if(personEmail == null || !filtro.test(personEmail)) {
			$("#div_error_info_person").prepend("<div id='div_person_email_error' class='alert alert-danger'>Email inválido!</div>");
            return;
        }
		
		var filmsVoted =  computeVotes(personName, personEmail);
		$.ajax({  
			type: "POST",
			contentType: "application/json",
			dataType: "json",
			accept: "application/json",
	        data: filmsVoted,   
	        url: currentUrl + "savefilmvote/",  
	        success: function(response) {  
	        	createGeneralRanking();
	        	createUserRanking(response.id);
	        },
			error: function(http) {  
				var response = JSON.parse(http.responseText);
				$("#div_error_info_person").prepend("<div id='div_person_name_error' class='alert alert-danger'>" + response.error + "</div>");
	        }  
	    });
		
	});
	
	$("#div_film1").click(function() {
		var filmeId = $("#film1_id").val();
		votedFilms.push(filmeId);
		showFilmsToCompare();
	});
	
	$("#div_film2").click(function() {
		var filmeId = $("#film2_id").val();
		votedFilms.push(filmeId);
		showFilmsToCompare();
	});
	
	$("#menu_item_generamranking").click(function() {
		$("#div_person_ranking").hide();
		createGeneralRanking();
	});
	
	function showFilmsToCompare() {
		var filmsVote = null;
		var index = Math.floor(Math.random() * filmsToVote.length);
		if (filmsToVote.length > 0) {
			filmsVote = filmsToVote[index].films;
			$("#div_film1").html(
					"<img id='img_film1' src='" + currentUrl + filmsVote.film1.urlImage + "'/>"
					+ "<input id='film1_id' type='hidden' value='" + filmsVote.film1.id +  "'/>"
			);
			$("#div_film2").html(
					"<img id='img_film2' src='" + currentUrl + filmsVote.film2.urlImage + "'/>"
					+ "<input id='film2_id' type='hidden' value='" + filmsVote.film2.id +  "'/>"
			);
		}
		
		if (filmsToVote.length >= 1) {
			filmsToVote.splice(index,1);
		} else if (filmsToVote.length == 0){
			$("#div_content_films").hide();
			$("#div_titulo_pergunta").hide();
			$("#div_info_person").show();
			$("#div_button_confirma").show();	
		}
	}
	
	function computeVotes(personName, personEmail) {
		var voteQuantityPerFilm = new Array();
		$.each(votedFilms, function(index, idFilm) {
			var quantity = votedFilms.countElement(idFilm,votedFilms);
			if (verifyDuplicate(idFilm, voteQuantityPerFilm)) {
				voteQuantityPerFilm.push(getFilmVote(idFilm, quantity, personName, personEmail));
			}
		});
		var filmsVoted = {
				filmsVoted: voteQuantityPerFilm
		};
		var filmsVotedJson = JSON.stringify(filmsVoted);
		return filmsVotedJson;
	}
	
	function verifyDuplicate(idFilm, voteQuantityPerFilm) {
		if (voteQuantityPerFilm.length == 0) return true;
		for (var i = 0; i < voteQuantityPerFilm.length; i++) {
			if (voteQuantityPerFilm[i].film.id == parseInt(idFilm)) {
				return false; 
			}
		}
		return true;
	}
	
	function getFilmVote(idF, quantity, personName, personEmail) {
		var film = getFilm(idF);
		var filmVote = {
				film: film,
				numberOfVotes: quantity,
				name: personName,
				email: personEmail
		};
		return filmVote;
	}
	
	function getFilm(idFilm) {
		var film = null;
		avalaibleFilms.forEach(function(value){
			if (value.id == parseInt(idFilm)) {
				film = value; 
			}
		}) ;
		return film;
	}
	
	function createGeneralRanking() {
		$("#div_ranking").show("slow");
		$("#div_content_filmvote").hide();
		var chartData = new Array();
		$.getJSON(currentUrl + "ranking/ranking-geral", "", function(rankingGeral) {
			filmsRanking = rankingGeral.rankingList;
			for (var i = 0; i < filmsRanking.length; i++) {
				chartData.push(getDataToRanking(filmsRanking[i].filmName, filmsRanking[i].numberOfVotes));
			}
			$("#div_general_ranking").highcharts({
	            chart: {
	                type: "column"
	            },
	            title: {
	                text: "General Ranking"
	            },
	            xAxis: {
	                categories: getCategories()
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: "Number of Votes"
	                }
	            },
	            tooltip: {
	                headerFormat: "<span style='font-size:10px'>{point.key}</span><table>",
	                pointFormat: "<tr><td style='color:{series.color};padding:0'>{series.name}: </td>" +
	                             "<td style='color:{series.color};padding:0;'><b>{point.y}</b></td></tr>",
	                footerFormat: "</table>",
	                shared: true,
	                useHTML: true
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	            series: [{
	                name: "Votes",
	                data: chartData
	            }]
	        });
		});
	}
	
	function createUserRanking(personId) {
		var chartDataUser = new Array();
		$.getJSON(currentUrl + "ranking/ranking-person/" + personId, "", function(rankingGeral) {
			filmsRanking = rankingGeral.rankingList;
			for (var i = 0; i < filmsRanking.length; i++) {
				chartDataUser.push(getDataToRanking(filmsRanking[i].filmName, filmsRanking[i].numberOfVotes));
			}
			
			$("#div_person_ranking").highcharts({
	            chart: {
	                type: "column"
	            },
	            title: {
	                text: "Your Ranking"
	            },
	            xAxis: {
	                categories: getCategories()
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: "Number of Votes"
	                }
	            },
	            tooltip: {
	                headerFormat: "<span style='font-size:10px'>{point.key}</span><table>",
	                pointFormat: "<tr><td style='color:{series.color};padding:0'>{series.name}: </td>" +
	                             "<td style='color:{series.color};padding:0;'><b>{point.y}</b></td></tr>",
	                footerFormat: "</table>",
	                shared: true,
	                useHTML: true
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	            series: [{
	                name: "Votes",
	                data: chartDataUser
	            }]
	        });
		});
	}
	
	function getCategories() {
		var categories = new Array();
		filmsRanking.forEach(function(film) {
			categories.push(film.name);
		});
		return categories;
	}
	
	function getDataToRanking(filmName, numberOfVotes) {
		var data = [filmName, numberOfVotes];
		return data;
	}
	
	Array.prototype.countElement = function(item, array) {
	    var count = 0;
	    $.each(array, function(index,value) { 
	    	if (value === item) count++; 
	    });
	    return count;
	};
});