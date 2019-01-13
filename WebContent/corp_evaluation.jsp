<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">




function beforesubmit(){
	var el = document.corp1;

	
	var promotion_star = document.getElementsByClassName("starR1 on");
	var welfare_star = document.getElementsByClassName("starR2 on");
	var balance_star = document.getElementsByClassName("starR3 on");
	var culture_star = document.getElementsByClassName("starR4 on");
	var management_star = document.getElementsByClassName("starR5 on");
	
	el.promotion_score.value=promotion_star.length;
	el.welfare_score.value=welfare_star.length;
	el.balance_score.value=balance_star.length;
	el.culture_score.value=culture_star.length;
	el.management_score.value=management_star.length;

	el.submit();

}




function insertoccupation() {
	
	var sp=document.getElementById("addaccupation");
	var occupation = document.getElementById("occupation1").value;
	
	
	var inselect = document.createElement("select");
	inselect.setAttribute("class", "form-control");
	inselect.setAttribute("id", "occupation2");
	inselect.setAttribute("name", "occupation2");
	
	var deleteselect = document.getElementById("occupation2");
 	if (deleteselect != null){
 		sp.removeChild(deleteselect); 
 	}
	
	if(occupation =="경영·사무") {

		var option1 = document.createElement("option");
		option1.text = "기획·전략·경영";
		option1.value = "기획·전략·경영";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "사무·총무·법무";
		option2.value = "사무·총무·법무";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "인사·노무·교육";
		option3.value = "인사·노무·교육";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "경리·회계·결산";
		option4.value = "경리·회계·결산";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "재무·세무·IR";
		option5.value = "재무·세무·IR";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "사무보조·문서작성";
		option6.value = "사무보조·문서작성";
		inselect.add(option6,null);
		
		sp.appendChild(inselect);
	}
	
	else if(occupation =="마케팅·광고·홍보") {
		
		var option1 = document.createElement("option");
		option1.text = "마케팅·광고·분석";
		option1.value = "마케팅·광고·분석";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "홍보·PR";
		option2.value = "홍보·PR";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "전시·컨벤션";
		option3.value = "전시·컨벤션육";
		inselect.add(option3,null);

		
		sp.appendChild(inselect);
	}
	
	else if(occupation =="IT·인터넷") {


		var option1 = document.createElement("option");
		option1.text = "웹프로그래머";
		option1.value = "웹프로그래머";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "응용프로그래머";
		option2.value = "응용프로그래머";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "시스템프로그래머";
		option3.value = "시스템프로그래머";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "DBA·데이터베이스";
		option4.value = "DBA·데이터베이스";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "네트워크·서버보안";
		option5.value = "네트워크·서버보안";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "웹기획·PM";
		option6.value = "웹기획·PM";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "웹마케팅";
		option7.value = "웹마케팅";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "컨텐츠·사이트운영";
		option8.value = "컨텐츠·사이트운영";
		inselect.add(option8,null);
		
		var option9 = document.createElement("option");
		option9.text = "HTML·퍼블리싱·UI개발";
		option9.value = "HTML·퍼블리싱·UI개발";
		inselect.add(option9,null);
		
		var option10 = document.createElement("option");
		option10.text = "웹디자인";
		option10.value = "웹디자인";
		inselect.add(option10,null);
		
		var option11 = document.createElement("option");
		option11.text = "QA·테스터·검증";
		option11.value = "QA·테스터·검증";
		inselect.add(option11,null);
		
		var option12 = document.createElement("option");
		option12.text = "게임";
		option12.value = "게임";
		inselect.add(option12,null);
		
		var option13 = document.createElement("option");
		option13.text = "ERP시스템 분석·설계";
		option13.value = "ERP시스템 분석·설계";
		inselect.add(option13,null);
		
		var option14 = document.createElement("option");
		option14.text = "IT·디자인·컴퓨터강사";
		option14.value = "IT·디자인·컴퓨터강사";
		inselect.add(option14,null);
		
		var option15 = document.createElement("option");
		option15.text = "동영상 제작·편집";
		option15.value = "동영상 제작·편집";
		inselect.add(option15,null);
		
		var option16 = document.createElement("option");
		option16.text = "빅데이터·AI(인공지능)";
		option16.value = "빅데이터·AI(인공지능)";
		inselect.add(option16,null);
		
		var option17 = document.createElement("option");
		option17.text = "소프트웨어·하드웨어";
		option17.value = "소프트웨어·하드웨어";
		inselect.add(option17,null);
		
		sp.appendChild(inselect);
	}
	
	else if(occupation =="디자인") {
		

		var option1 = document.createElement("option");
		option1.text = "그래픽디자인·CG";
		option1.value = "그래픽디자인·CG";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "출판·편집디자인";
		option2.value = "출판·편집디자인";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "제품·산업디자인";
		option3.value = "제품·산업디자인";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "캐릭터·애니메이션";
		option4.value = "캐릭터·애니메이션";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "광고·시각디자인";
		option5.value = "광고·시각디자인";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "의류·패션·잡화디자인";
		option6.value = "의류·패션·잡화디자인";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "전시·공간디자인";
		option7.value = "전시·공간디자인";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "디자인 기타";
		option8.value = "디자인 기타";
		inselect.add(option8,null);
		
		
		sp.appendChild(inselect);
	}
	else if(occupation =="무역·유통") {


		var option1 = document.createElement("option");
		option1.text = "해외영업·무역영업";
		option1.value = "해외영업·무역영업";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "수출입·무역사무";
		option2.value = "수출입·무역사무";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "구매·자재";
		option3.value = "구매·자재";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "상품기획·MD";
		option4.value = "상품기획·MD";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "유통·물류·재고";
		option5.value = "유통·물류·재고";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "배송·택배·운송";
		option6.value = "배송·택배·운송";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "운전·기사";
		option7.value = "운전·기사";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "화물·중장비";
		option8.value = "화물·중장비";
		inselect.add(option8,null);
		
		sp.appendChild(inselect);
	}
	
	else if(occupation =="영업·고객상담") {
		

		var option1 = document.createElement("option");
		option1.text = "제품·서비스영업";
		option1.value = "제품·서비스영업";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "금융·보험영업";
		option2.value = "금융·보험영업";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "IT·솔루션·기술영업";
		option3.value = "IT·솔루션·기술영업";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "영업관리·지원·영업기획";
		option4.value = "영업관리·지원·영업기획";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "광고영업";
		option5.value = "광고영업";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "판매·캐셔·매장관리";
		option6.value = "판매·캐셔·매장관리";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "단순홍보·회원관리";
		option7.value = "단순홍보·회원관리";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "아웃바운드TM";
		option8.value = "아웃바운드TM";
		inselect.add(option8,null);
		
		var option9 = document.createElement("option");
		option9.text = "고객상담·인바운드";
		option9.value = "고객상담·인바운드";
		inselect.add(option9,null);
		
		var option10 = document.createElement("option");
		option10.text = "CS관리·강의";
		option10.value = "CS관리·강의";
		inselect.add(option10,null);
		
		sp.appendChild(inselect);
	}
	
	else if(occupation =="서비스") {
		

		var option1 = document.createElement("option");
		option1.text = "요리·영양·제과제빵·바리스타";
		option1.value = "요리·영양·제과제빵·바리스타";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "설치·정비A/S";
		option2.value = "설치·정비A/S";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "시설·보안·경비·안전";
		option3.value = "시설·보안·경비·안전";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "레저·스포츠";
		option4.value = "레저·스포츠";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "여행·항공·숙박";
		option5.value = "여행·항공·숙박";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "뷰티·미용·애완";
		option6.value = "뷰티·미용·애완";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "주차·세차·주유";
		option7.value = "주차·세차·주유";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "청소·가사·육아";
		option8.value = "청소·가사·육아";
		inselect.add(option8,null);
		
		var option9 = document.createElement("option");
		option9.text = "이벤트·웨딩도우미";
		option9.value = "이벤트·웨딩도우미";
		inselect.add(option9,null);
		
		sp.appendChild(inselect);
	}
	
	
	else if(occupation =="연구개발·설계") {
		

		var option1 = document.createElement("option");
		option1.text = "자동차·조선·기계";
		option1.value = "자동차·조선·기계";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "반도체·디스플레이";
		option2.value = "반도체·디스플레이";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "화학·에너지환경";
		option3.value = "화학·에너지환경";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "전기·전자·제어";
		option4.value = "전기·전자·제어";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "기계설계·CAD·CAM";
		option5.value = "기계설계·CAD·CAM";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "통신기술·네트워크구축";
		option6.value = "통신기술·네트워크구축";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "바이오·제약식품";
		option7.value = "바이오·제약식품";
		inselect.add(option7,null);
		
		sp.appendChild(inselect);
	}
	
	
	else if(occupation =="생산·제조") {
		

		var option1 = document.createElement("option");
		option1.text = "생산관리·공정관리·품질관리";
		option1.value = "생산관리·공정관리·품질관리";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "생산·제조·설비·조립";
		option2.value = "생산·제조·설비·조립";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "포장·가공";
		option3.value = "포장·가공";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "섬유·의류·패션";
		option4.value = "섬유·의류·패션";
		inselect.add(option4,null);
		
		sp.appendChild(inselect);
	}
	
	
	
	else if(occupation =="교육") {


		var option1 = document.createElement("option");
		option1.text = "유치원·보육교사";
		option1.value = "유치원·보육교사";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "초중고·특수학교";
		option2.value = "초중고·특수학교";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "대학교수·강사·행정직";
		option3.value = "대학교수·강사·행정직";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "보습학원·입시학원";
		option4.value = "보습학원·입시학원";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "학원상담·관리·운영";
		option5.value = "학원상담·관리·운영";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "학습지·과외·방문교사";
		option6.value = "학습지·과외·방문교사";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "주차·세차·주유";
		option7.value = "주차·세차·주유";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "외국어교육";
		option8.value = "외국어교육";
		inselect.add(option8,null);
		
		var option9 = document.createElement("option");
		option9.text = "자격증·기술·전문교육";
		option9.value = "자격증·기술·전문교육";
		inselect.add(option9,null);
		
		var option10 = document.createElement("option");
		option10.text = "교재기획·교수설계";
		option10.value = "교재기획·교수설계";
		inselect.add(option10,null);
		
		sp.appendChild(inselect);
	}
	
	
	else if(occupation =="건설") {


		var option1 = document.createElement("option");
		option1.text = "건축·설계·인테리어";
		option1.value = "건축·설계·인테리어";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "시공·현장·감리·공무";
		option2.value = "시공·현장·감리·공무";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "토목·조경·도시·측량";
		option3.value = "토목·조경·도시·측량";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "전기·소방·통신·안전";
		option4.value = "전기·소방·통신·안전";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "환경·플랜트";
		option5.value = "환경·플랜트";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "부동산·중개·분양·경매";
		option6.value = "부동산·중개·분양·경매";
		inselect.add(option6,null);
		
		sp.appendChild(inselect);
	}
	
	else if(occupation =="의료") {

		var option1 = document.createElement("option");
		option1.text = "의사·치과·한의사";
		option1.value = "의사·치과·한의사";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "약사·한약사·약무보조";
		option2.value = "약사·한약사·약무보조";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "간호사";
		option3.value = "간호사";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "간호조무사";
		option4.value = "간호조무사";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "의료기사";
		option5.value = "의료기사";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "사무·원무·코디";
		option6.value = "사무·원무·코디";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "수의사·수의간호";
		option7.value = "수의사·수의간호";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "의료직기타";
		option8.value = "의료직기타";
		inselect.add(option8,null);
		
		sp.appendChild(inselect);
	}
	
	
	else if(occupation =="미디어") {

		var option1 = document.createElement("option");
		option1.text = "감독·연출·PD";
		option1.value = "감독·연출·PD";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "영상·사진·촬영";
		option2.value = "영상·사진·촬영";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "광고제작·카피·CF";
		option3.value = "광고제작·카피·CF";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "아나운서·리포터·성우";
		option4.value = "아나운서·리포터·성우";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "기자";
		option5.value = "기자";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "작가·시나리오";
		option6.value = "작가·시나리오";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "연예·엔터테인먼트";
		option7.value = "연예·엔터테인먼트";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "인쇄·출판·편집";
		option8.value = "인쇄·출판·편집";
		inselect.add(option8,null);
		
		var option9 = document.createElement("option");
		option9.text = "영화·배급";
		option9.value = "영화·배급";
		inselect.add(option9,null);
		
		var option10 = document.createElement("option");
		option10.text = "음악·음향";
		option10.value = "음악·음향";
		inselect.add(option10,null);
		
		var option11 = document.createElement("option");
		option11.text = "공연·전시·무대·스텝";
		option11.value = "공연·전시·무대·스텝";
		inselect.add(option11,null);
		
		sp.appendChild(inselect);
	}
	
	
	else if(occupation =="전문·특수직") {

		var option1 = document.createElement("option");
		option1.text = "경영분석·컨설턴트";
		option1.value = "경영분석·컨설턴트";
		inselect.add(option1,null);
		
		var option2 = document.createElement("option");
		option2.text = "채권·심사·보험·보상";
		option2.value = "채권·심사·보험·보상";
		inselect.add(option2,null);
		
		var option3 = document.createElement("option");
		option3.text = "회계·세무·CPA";
		option3.value = "회계·세무·CPA";
		inselect.add(option3,null);
		
		var option4 = document.createElement("option");
		option4.text = "노무·헤드헌터·직업상담";
		option4.value = "노무·헤드헌터·직업상담";
		inselect.add(option4,null);
		
		var option5 = document.createElement("option");
		option5.text = "리서치·통계·설문";
		option5.value = "리서치·통계·설문";
		inselect.add(option5,null);
		
		var option6 = document.createElement("option");
		option6.text = "도서관사서";
		option6.value = "도서관사서";
		inselect.add(option6,null);
		
		var option7 = document.createElement("option");
		option7.text = "법률·특허·상표";
		option7.value = "법률·특허·상표";
		inselect.add(option7,null);
		
		var option8 = document.createElement("option");
		option8.text = "외국어·번역·통역";
		option8.value = "외국어·번역·통역";
		inselect.add(option8,null);
		
		var option9 = document.createElement("option");
		option9.text = "보안·경호";
		option9.value = "보안·경호";
		inselect.add(option9,null);
		
		var option10 = document.createElement("option");
		option10.text = "사회복지·요양보호·자원봉사";
		option10.value = "사회복지·요양보호·자원봉사";
		inselect.add(option10,null);
		
		sp.appendChild(inselect);
	}
}






</script>

<style type="text/css">


 .starR1, .starR2, .starR3, .starR4, .starR5{
  background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
  background-size: auto 100%;
  width: 30px;
  height: 30px;
  display: inline-block;
  text-indent: -9999px;
  cursor: pointer;
}
.starR1.on, .starR2.on, .starR3.on, .starR4.on, .starR5.on{background-position:0 0;} 

</style>
<script type="text/javascript">

	function go() {
		document.
	}
</script>

</head>

<body>
	<div class="container">
		<h2>기업리뷰 작성</h2>
		<p>입력하신 모든 정보는 익명으로 처리됩니다.</p>
		
 		<form action="corp_name.do" method="get" name="corp2">

			<div class="form-group col-md-6">
				<label class="control-label col-md-2" for="corp_name">기업명:</label> 
		
				<input type="text" class="form-control" id="corp_name" name="corp_name" value="${SELECT_NAME}" >
				<input type="hidden" name="code" value="corpname_code">
				<input type="submit" class="btn" id="corp_name_btn" name="corp_name_btn" value="검색" style="float:right" action="corp_name.do" method="get">
			</div>
		</form>
			
			
		<form action="evaluation.do" method="post" name="corp1">
			
			<input type="hidden" name="corp_name" value="${SELECT_NAME}">

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
			<div class="form-group col-md-8">
				<label class="control-label col-md-2" for="occupation1">직종:</label> 
				<select class="form-control" id="occupation1" name="occupation1" onchange="javascript:insertoccupation();">
				
				
					<option value="">직종을 선택하세요.</option>
					<option value="경영·사무">경영·사무</option>
					<option value="마케팅·광고·홍보">마케팅·광고·홍보</option>
					<option value="IT·인터넷">IT·인터넷</option>
					<option value="디자인">디자인</option>
					<option value="무역·유통">무역·유통</option>
					<option value="영업·고객상담">영업·고객상담</option>
					<option value="서비스">서비스</option>
					<option value="연구개발·설계">연구개발·설계</option>
					<option value="생산·제조">생산·제조</option>
					<option value="교육">교육</option>
					<option value="건설">건설</option>
					<option value="의료">의료</option>
					<option value="미디어">미디어</option>
					<option value="전문·특수직">전문·특수직</option>
				</select>
			</div>

			<div class="form-group col-md-8" id="addaccupation"></div>

			<div class="form-group col-md-8">
				<label class="control-label col-md-2" for="position">직책:</label> 
				<select class="form-control" id="position" name="position">
					<option value="">직책을 선택하세요.</option>
					<option value="임원급">임원급</option>
					<option value="부장급">부장급</option>
					<option value="차장급">차장급</option>
					<option value="과장급">과장급</option>
					<option value="대리급">대리급</option>
					<option value="주임급">주임급</option>
					<option value="사원급">사원급</option>
				</select>
			</div>
			
			<div class="form-group col-md-8">
				<label class="control-label col-md-2" for="education_level">학력:</label> 
				<select class="form-control" id="education_level" name="education_level">
					<option value="">학력을 선택하세요.</option>
					<option value="대학원(박사)">대학원(박사)</option>
					<option value="대학원(석사)">대학원(석사)</option>
					<option value="대졸">대졸</option>
					<option value="초대졸">초대졸</option>
					<option value="고졸">고졸</option>
					<option value="기타">기타</option>
				</select>
			</div>

			<div class="form-group col-md-8">
				<label class="control-label col-md-2" for="career">경력:</label> 
				<select class="form-control" id="career" name="career">
					<option value="">경력을 선택하세요.</option>
					<option value="1년차">1년차</option>
					<option value="2년차">2년차</option>
					<option value="3년차">3년차</option>
					<option value="4년차">4년차</option>
					<option value="5년차">5년차</option>
					<option value="6년차">6년차</option>
					<option value="7년차">7년차</option>
					<option value="8년차">8년차</option>
					<option value="9년차">9년차</option>
					<option value="10년차">10년차</option>
					<option value="11년차">11년차</option>
					<option value="12년차">12년차</option>
					<option value="13년차">13년차</option>
					<option value="14년차">14년차</option>
					<option value="15년차">15년차</option>
					<option value="16년차">16년차</option>
					<option value="17년차">17년차</option>
					<option value="18년차">18년차</option>
					<option value="19년차">19년차</option>
					<option value="20년차">20년차</option>
					<option value="21년차">21년차</option>
					<option value="22년차">22년차</option>
					<option value="23년차">23년차</option>
					<option value="24년차">24년차</option>
					<option value="25년차 이상">25년차 이상</option>
				</select>
			</div>
			
			<div class="form-group col-md-6">
	 			<label class="control-label col-md-2" for="annual_income">연봉:</label> 
				<input type="text" class="form-control" id="annual_income" name="annual_income" placeholder="연봉을 입력하세요.">	
			</div>

			
			
			
			
			
			
			
			
			
			
			
			<div class="form-group col-md-8">
				<label class="control-label col-md-2" for="work_type">고용형태:</label> 
				<select class="form-control" id="work_type" name="work_type">
					<option value="">고용형태를 선택하세요.</option>
					<option value="정규직">정규직</option>
					<option value="계약직">계약직</option>
					<option value="파견직">파견직</option>
					<option value="인턴">인턴</option>
					<option value="프리랜서">프리랜서</option>
					<option value="아르바이트">아르바이트</option>
					<option value="기타">기타</option>
				</select>
			</div>


			
			
			
			<div class="form-group col-md-12">
				<label class="control-label col-md-2" for="promotion">승진기회 및 가능성:</label>
				<div class="col-md-10 starRev" id="promotion" name="promotion">
					<span class="starR1 on">별1</span>
					<span class="starR1">별2</span>
					<span class="starR1">별3</span>
					<span class="starR1">별4</span>
					<span class="starR1">별5</span>          
				</div>
			</div>

			<div class="form-group col-md-12">
				<label class="control-label col-md-2" for="welfare">복지 및 급여:</label>
				<div class="col-md-10 starRev" id="welfare" name="welfare">
					<span class="starR2 on">별1</span>
					<span class="starR2">별2</span>
					<span class="starR2">별3</span>
					<span class="starR2">별4</span>
					<span class="starR2">별5</span>          
				</div>
			</div>

			<div class="form-group col-md-12">
				<label class="control-label col-md-2" for="balance">업무와 삶의 균형:</label> 
				<div class="col-md-10 starRev" id="balance" name="balance">
					<span class="starR3 on">별1</span>
					<span class="starR3">별2</span>
					<span class="starR3">별3</span>
					<span class="starR3">별4</span>
					<span class="starR3">별5</span>          
				</div>
			</div>	

			<div class="form-group col-md-12">
				<label class="control-label col-md-2" for="culture">사내문화:</label> 
				<div class="col-md-10 starRev" id="culture" name="culture">
					<span class="starR4 on">별1</span>
					<span class="starR4">별2</span>
					<span class="starR4">별3</span>
					<span class="starR4">별4</span>
					<span class="starR4">별5</span>          
				</div>
			</div>	

			<div class="form-group col-md-12">
				<label class="control-label col-md-2" for="management">경영진:</label>
				<div class="col-md-10 starRev" id="management" name="management">
					<span class="starR5 on">별1</span>
					<span class="starR5">별2</span>
					<span class="starR5">별3</span>
					<span class="starR5">별4</span>
					<span class="starR5">별5</span>          
				</div>
			</div>	
			

			<div class="form-group col-md-8"  > 
				<input name="promotion_score" type="hidden" value="">
				<input name="welfare_score" type="hidden" value="">
				<input name="balance_score" type="hidden" value="">
				<input name="culture_score" type="hidden" value="">
				<input name="management_score" type="hidden" value="">
				<input name="code" type="hidden" value="corp_code">
				<input type="button" class="form-control btn-primary btn-lg " onclick="beforesubmit();" value="제출하기">
			</div>

		</form>
	</div>

	
	
	<script type="text/javascript">
		$('.starRev span').click(function(){
		  $(this).parent().children('span').removeClass('on');
		  $(this).addClass('on').prevAll('span').addClass('on');
		  return false;
		});

	</script>


</body>
</html>