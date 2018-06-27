<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	//knob
	$(function() {
		$(".knob").knob({
			'draw' : function() {
				$(this.i).val(this.cv + '%')
			}
		})
	});

	//carousel
	$(document).ready(function() {
		$("#owl-slider").owlCarousel({
			navigation : true,
			slideSpeed : 300,
			paginationSpeed : 400,
			singleItem : true

		});
	});

	//custom select box

	$(function() {
		$('select.styled').customSelect();
	});
	/* ---------- doughnut ---------- */
	/* ---- 미정 처리 영역 시작(2) ---- */
	$(document).ready(function() {
		 var doughnutData = [
		        {
		            value: 30,
		            color:"#F7464A"
		        },
		        {
		            value : 50,
		            color : "#46BFBD"
		        },
		        {
		            value : 100,
		            color : "#FDB45C"
		        },
		        {
		            value : 40,
		            color : "#BCE55C"
		        },
		        {
		            value : 120,
		            color : "#D1B2FF"
		        }
		    ];
		    new Chart(document.getElementById("doughnut").getContext("2d")).Doughnut(doughnutData);
	});
	/* ---- 미정 처리 영역 끝(2) ---- */
/* ---- 세중 처리 영역 시작(1) ---- */
	
	/* --------- Ajax 준비 단계 ---------- */
	function getXHR() {
		var req;
		try {
			req = new XMLHttpRequest();
		} catch (e) {
			try {
				req = new ActiveXObject('Msxml2.XMLHTTP');
			} catch (e) {
				req = new ActiveXObject('Microsoft.XMLHTTP');
			}
		}
		return req;
	}
	
	/* ---------- Map ---------- */
	$(function() {
		$('#map').vectorMap({
			map : 'world_mill_en',

			onRegionClick : function(event, code) {
				var map = $('#map').vectorMap('get', 'mapObject');
				var regionName = map.getRegionName(code);
				
				// 전송 버튼이 눌리면 실행되는 '이벤트 핸들러'
				var req = getXHR();
				// 비동기 통신 시 처리할 콜백함수 정의
				req.onreadystatechange = function() {
					var checkAjax = document.getElementById("checkAjax");
					if (req.readyState == 4) { // 통신 준비 완료 시
						if (req.status == 200) { // 통신이 성공 한 경우
							checkAjax.innerHTML = req.responseText;
							/* ---- 미정 처리 영역 시작(2) ---- */
							var $value1 = $('#value1');	
							var value1 = $value1.val();	
							var $value2 = $('#value2');	
							var value2 = $value2.val();	
							var $value3 = $('#value3');	
							var value3 = $value3.val();	
							var $value4 = $('#value4');	
							var value4 = $value4.val();	
							var $value5 = $('#value5');	
							var value5 = $value5.val();	
							
							 var doughnutData = [
		        {
		            value: parseInt(value1),
		            color:"#F7464A"
		        },
		        {
		            value : parseInt(value2),
		            color : "#46BFBD"
		        },
		        {
		            value : parseInt(value3),
		            color : "#FDB45C"
		        },
		        {
		            value : parseInt(value4),
		            color : "#BCE55C"
		        },
		        {
		            value : parseInt(value5),
		            color : "#D1B2FF"
		        }
		    ];
		    new Chart(document.getElementById("doughnut").getContext("2d")).Doughnut(doughnutData);
						 	/* ---- Mij 처리 영역 끝(2) ---- */
						} else { // 통신이 실패한 경우
							checkAjax.innerHTML = "서버 에러 : "+req.status;
						}
					} else {
						checkAjax.innerHTML = "통신 중..";
					}
				}

			
				// 서버와 비동기 통신 개시
				req.open('GET','<%=request.getContextPath()%>/check/checkAjax.t?flag=1&regionName=' + regionName, true);
				//       방식     요청URL 및 데이터														비동기
				req.send(null);
				// send()요청 두 가지 : null은 GET방식

				/* $('#test').append(regionName + "&nbsp;"); */
			},
			
			series : {
				regions : [ {
					scale : [ '#353535', '#FFFF24' ],
					normalizeFunction : 'polynomial',
					values : {
                     	 "KR" : 9,
                         "US" : 9,
                         "CN" : 0.5,
                         "FR" : 0.1,
                         "JP" : 0.3,
                         "AF" : 0,
                         "AL" : 0,
                         "DZ" : 0,
                         "AO" : 0,
                         "AG" : 0,
                         "AR" : 0,
                         "AM" : 0,
                         "AU" : 0,
                         "AT" : 0,
                         "AZ" : 0,
                         "BS" : 0,
                         "BH" : 0,
                         "BD" : 0,
                         "BB" : 0,
                         "BY" : 0,
                         "BE" : 0,
                         "BZ" : 0,
                         "BJ" : 0,
                         "BT" : 0,
                         "BO" : 0,
                         "BA" : 0,
                         "BW" : 0,
                         "BR" : 0,
                         "BN" : 0,
                         "BG" : 0,
                         "BF" : 0,
                         "BI" : 0,
                         "KH" : 0,
                         "CM" : 0,
                         "CA" : 0,
                         "CV" : 0,
                         "CF" : 0,
                         "TD" : 0,
                         "CL" : 0,
                         "CO" : 0,
                         "KM" : 0,
                         "CD" : 0,
                         "CG" : 0,
                         "CR" : 0,
                         "CI" : 0,
                         "HR" : 0,
                         "CY" : 0,
                         "CZ" : 0,
                         "DK" : 0,
                         "DJ" : 0,
                         "DM" : 0,
                         "DO" : 0,
                         "EC" : 0,
                         "EG" : 0,
                         "SV" : 0,
                         "GQ" : 0,
                         "ER" : 0,
                         "EE" : 0,
                         "ET" : 0,
                         "FJ" : 0,
                         "FI" : 0,
                         "GA" : 0,
                         "GM" : 0,
                         "GE" : 0,
                         "DE" : 0,
                         "GH" : 0,
                         "GR" : 0,
                         "GD" : 0,
                         "GT" : 0,
                         "GN" : 0,
                         "GW" : 0,
                         "GY" : 0,
                         "HT" : 0,
                         "HN" : 0,
                         "HK" : 0,
                         "HU" : 0,
                         "IS" : 0,
                         "IN" : 0,
                         "ID" : 0,
                         "IR" : 0,
                         "IQ" : 0,
                         "IE" : 0,
                         "IL" : 0,
                         "IT" : 0,
                         "JM" : 0,
                         "JO" : 0,
                         "KZ" : 0,
                         "KE" : 0,
                         "KI" : 0,
                         "KW" : 0,
                         "KG" : 0,
                         "LA" : 0,
                         "LV" : 0,
                         "LB" : 0,
                         "LS" : 0,
                         "LR" : 0,
                         "LY" : 0,
                         "LT" : 0,
                         "LU" : 0,
                         "MK" : 0,
                         "MG" : 0,
                         "MW" : 0,
                         "MY" : 0,
                         "MV" : 0,
                         "ML" : 0,
                         "MT" : 0,
                         "MR" : 0,
                         "MU" : 0,
                         "MX" : 0,
                         "MD" : 0,
                         "MN" : 0,
                         "ME" : 0,
                         "MA" : 0,
                         "MZ" : 0,
                         "MM" : 0,
                         "NA" : 0,
                         "NP" : 0,
                         "NL" : 0,
                         "NZ" : 0,
                         "NI" : 0,
                         "NE" : 0,
                         "NG" : 0,
                         "NO" : 0,
                         "OM" : 0,
                         "PK" : 0,
                         "PA" : 0,
                         "PG" : 0,
                         "PY" : 0,
                         "PE" : 0,
                         "PH" : 0,
                         "PL" : 0,
                         "PT" : 0,
                         "QA" : 0,
                         "RO" : 0,
                         "RU" : 0,
                         "RW" : 0,
                         "WS" : 0,
                         "ST" : 0,
                         "SA" : 0,
                         "SN" : 0,
                         "RS" : 0,
                         "SC" : 0,
                         "SL" : 0,
                         "SG" : 0,
                         "SK" : 0,
                         "SI" : 0,
                         "SB" : 0,
                         "ZA" : 0,
                         "ES" : 0,
                         "LK" : 0,
                         "KN" : 0,
                         "LC" : 0,
                         "VC" : 0,
                         "SD" : 0,
                         "SR" : 0,
                         "SZ" : 0,
                         "SE" : 0,
                         "CH" : 0,
                         "SY" : 0,
                         "TW" : 0,
                         "TJ" : 0,
                         "TZ" : 0,
                         "TH" : 0,
                         "TL" : 0,
                         "TG" : 0,
                         "TO" : 0,
                         "TT" : 0,
                         "TN" : 0,
                         "TR" : 0,
                         "TM" : 0,
                         "UG" : 0,
                         "UA" : 0,
                         "AE" : 0,
                         "GB" : 0,
                         "UY" : 0,
                         "UZ" : 0,
                         "VU" : 0,
                         "VE" : 0,
                         "VN" : 0,
                         "YE" : 0,
                         "ZM" : 0,
                         "ZW" : 0
					}
				} ]
			},
			backgroundColor : '#eef3f7',
			onLabelShow : function(e, el, code) {
				el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
			}
		});
	});
/* ---- 세중 처리 영역 끝(1) ---- */
	
	
/* ---- 세중 처리 영역 시작(2) ---- */
  	function checkPlus(){
		// 전송 버튼이 눌리면 실행되는 '이벤트 핸들러'
		var req = getXHR();
 		var $regionN = $('#regionN');
		var regionName = $regionN.val();
		
		// 비동기 통신 시 처리할 콜백함수 정의
		req.onreadystatechange = function() {
			var plusMinus = document.getElementById("plusMinus");
			if (req.readyState == 4) { // 통신 준비 완료 시
				if (req.status == 200) { // 통신이 성공 한 경우
					plusMinus.innerHTML = req.responseText;
				} else { // 통신이 실패한 경우
					plusMinus.innerHTML = "서버 에러 : "+req.status;
				}
			} else {
				plusMinus.innerHTML = "통신 중..";
			}
		}
		
		// 서버와 비동기 통신 개시
		req.open('GET','<%=request.getContextPath()%>/check/checkAjax.t?flag=2&regionName=' + regionName, true);
		req.send(null);
	};
	
	function checkMinus(){
		// 전송 버튼이 눌리면 실행되는 '이벤트 핸들러'
		var req = getXHR();
 		var $regionN = $('#regionN');	// 제이쿼리 변수에 해당 아이디 대입
		var regionName = $regionN.val();	// value 읽는 함수 사용
		// 비동기 통신 시 처리할 콜백함수 정의
		req.onreadystatechange = function() {
			var plusMinus = document.getElementById("plusMinus");
			if (req.readyState == 4) { // 통신 준비 완료 시
				if (req.status == 200) { // 통신이 성공 한 경우
					plusMinus.innerHTML = req.responseText;
				} else { // 통신이 실패한 경우
					plusMinus.innerHTML = "서버 에러 : "+req.status;
				}
			} else {
				plusMinus.innerHTML = "통신 중..";
			}
		}
	
		// 서버와 비동기 통신 개시
		req.open('GET','<%=request.getContextPath()%>/check/checkAjax.t?flag=3&regionName='
						+ regionName, true);
		//       방식     요청URL 및 데이터														비동기
		req.send(null);
		// send()요청 두 가지 : null은 GET방식
	};

	/* ---- 세중 처리 영역 끝(2) ---- */

	/* ----미정 처리 영역 시작------ */
	////////////////1. 편집기
	CKEDITOR
			.replace(
					'content',
					{
						width : '100%',
						height : '600px', //에디터 가로 세로 
						filebrowserImageUploadUrl : '${pageContext.request.contextPath }/check/checkImageUpload.t',
						customConfig : '${pageContext.request.contextPath}/traveler/design/js/lib/ckeditor/config_oboe.js'
					}); // 업로드 탭 생성 및 경로 

	CKEDITOR.on('dialogDefinition', function(ev) {
		var dialogName = ev.data.name;
		var dialog = ev.data.definition.dialog;
		var dialogDefinition = ev.data.definition;
		if (dialogName == 'image') {
			dialog.on('show', function(obj) {
				this.selectPage('Upload');//업로드 탭으로 시작
			});
			dialogDefinition.removeContents('advanced');//자세히탭 제거
			dialogDefinition.removeContents('Link');//링크탭 제거 
		}
	})
	//버튼 글자는 ckeditor/lang/ko.js에서 "ok": 와 "cancel": 찾아서 수정하면 된다

	///////////////2. MainView에서 검색/작성 구분 
	//검색
	function checkList(form) {
	var frm = form;
	if(frm.city.value==""){
		alert("도시를 체크하세요");
		return;
	}
	if(frm.term.value==""&&frm.termDirect.value==""){
		
		alert("기간을 체크하세요 : "+frm.termDirect.value);
		return;
	}
	if(frm.money.value==""&&frm.moneyDirect.value==""){
		alert("비용을 체크하세요");
		return;
	}
	if(frm.route.value==""){
		alert("경로를 체크하세요");
		return;
	}
	if(frm.term.value==""){ //기간 직접 입력 시 플래그
		frm.termFlag.value="2";
	}
	if(frm.money.value==""){//비용 직접 입력 시 플래그
		frm.moneyFlag.value="2";
	}
	//alert("term : "+frm.termDirect.value)
	frm.action='<%=request.getContextPath()%>/check/checkList.t';
	frm.submit();
}
//작성
 function checkWrite(form) {
	var frm = form;
	if(frm.city.value==""){
		alert("도시를 체크하세요");
		return;
	}
	if(frm.term.value==""&&frm.termDirect.value==""){
		
		alert("기간을 체크하세요 : "+frm.termDirect.value);
		return;
	}
	if(frm.money.value==""&&frm.moneyDirect.value==""){
		alert("비용을 체크하세요");
		return;
	}
	if(frm.route.value==""){
		alert("경로를 체크하세요");
		return;
	}
	if(frm.term.value==""){ //기간 직접 입력 시 플래그
		frm.termFlag.value="2";
	}
	if(frm.money.value==""){//비용 직접 입력 시 플래그
		frm.moneyFlag.value="2";
	}
	//alert("term : "+frm.termDirect.value)
	frm.action='<%=request.getContextPath()%>/check/checkWriteForm.t';
	frm.submit();
}
	/////////////////3. 좋아요 버튼
	function favoritePlus(){
		var req = getXHR();		
		// 비동기 통신 시 처리할 콜백함수 정의
		req.onreadystatechange = function() {
			var favorite = document.getElementById("favorite");
			if (req.readyState == 4) { // 통신 준비 완료 시
				if (req.status == 200) { // 통신이 성공 한 경우
					favorite.innerHTML = req.responseText;
				} else { // 통신이 실패한 경우
					favorite.innerHTML = "서버 에러 : "+req.status;
				}
			} else {
				favorite.innerHTML = "통신 중..";
			}
		}
		
		// 서버와 비동기 통신 개시
		req.open('GET','<%=request.getContextPath()%>/travelBoard/favoriteAjax.t?flag=1&num=${travelBoardDto.num}&id=${travelBoardDto.writer}', true);
		req.send(null);
	}
	function favoriteMinus(){
		var req = getXHR();		

		// 비동기 통신 시 처리할 콜백함수 정의
		req.onreadystatechange = function() {
			var favorite = document.getElementById("favorite");
			if (req.readyState == 4) { // 통신 준비 완료 시
				if (req.status == 200) { // 통신이 성공 한 경우
					favorite.innerHTML = req.responseText;
				} else { // 통신이 실패한 경우
					favorite.innerHTML = "서버 에러 : "+req.status;
				}
			} else {
				favorite.innerHTML = "통신 중..";
			}
		}
		
		// 서버와 비동기 통신 개시
		req.open('GET','<%=request.getContextPath()%>/travelBoard/favoriteAjax.t?flag=2&num=${travelBoardDto.num}&id=${travelBoardDto.writer}', true);
		req.send(null);
	}

	
	
	///////////////4. 댓글 
	function comment(flag){
		var req = getXHR();		
		var $listNum = $('#listNum');	// 제이쿼리 변수에 해당 아이디 대입
		var listNum = $listNum.val();	// value 읽는 함수 사용
		if(flag==2){
			var $content = $('#content');	// 제이쿼리 변수에 해당 아이디 대입
			var content = $content.val().replace(/\n/g,"<br>");	// value 읽는 함수 사용
		}
		
		//alert("loginId : "+loginId+"listNum : "+listNum)
		// 비동기 통신 시 처리할 콜백함수 정의
		req.onreadystatechange = function() {
			var comment = document.getElementById("comment");
			if (req.readyState == 4) { // 통신 준비 완료 시
				if (req.status == 200) { // 통신이 성공 한 경우
					comment.innerHTML = req.responseText;
				} else { // 통신이 실패한 경우
					comment.innerHTML = "서버 에러 : "+req.status;
				}
			} else {
				comment.innerHTML = "통신 중..";
			}
		}
		
		// 서버와 비동기 통신 개시
		req.open('GET','<%=request.getContextPath()%>/travelBoard/commentAjax.t?flag='+ flag + '&listNum=' + listNum + '&content=' + content,true);
		req.send(null);
	}

	//5. 직접입력
	//1) term 
	function termDirectF() {
		$('#termDirect').focus(); //기간 직접입력에 포커스가 되면
		var termVal = $(':radio[name="term"]:checked').val(); //체크된 기간 라디오 버튼의 value값을 불러옴.
		//alert("termVal : "+termVal);
		$('input:radio[name=term]:input[value=' + termVal + ']').attr(
				"checked", false); //기간 라디오 버튼에 체크된 라디오 버튼과 일치하면 체크 해제.
	}
	function termRadioF() {
		$('#term').focus(); //기간 라디오 버튼을 클릭하면 
		//alert("radio in");
		$(':text([id=termDirect])').val(''); //기간 직접입력 칸에 있는 값을 초기화.
	}

	//2) money
	function moneyDirectF() {
		$('#moneyDirect').focus(); //기간 직접입력에 포커스가 되면
		var moneyVal = $(':radio[name="money"]:checked').val();
		//alert("moneyVal : "+moneyVal);
		$('input:radio[name=money]:input[value=' + moneyVal + ']').attr(
				"checked", false);
	}
	function moneyRadioF() {
		$('#money').focus();
		//alert("radio in");
		$(':text([id=moneyDirect])').val('');
	}

	/* ----미정 처리 영역 끝------ */
</script>

