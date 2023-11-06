function clearManagerMode(){
	location.href="/Ezen_teamB/jsp/index.jsp";
}


// 1. 회원관리 경로이동
function goToMemberList(){

	fetch('./memberList.jsp')
		.then(response => response.text())
		.then(data => {
			document.querySelector('.rightMainOutput').innerHTML = data;
		})
		.catch(error => {
			console.error('데이터를 가져오는 중 오류 발생:', error);
		});

}

// 2. 통계 경로이동
function goToStatistics(){
	/*
	fetch('./statistics.jsp')
		.then(response => response.text())
		.then(data => {
			document.querySelector('.rightMainOutput').innerHTML = data;
		})
		.catch(error => {
			console.error('데이터를 가져오는 중 오류 발생:', error);
		});
	*/

	fetch('./statistics.jsp')
		.then(response => response.text())
		.then(data => {
			// 'data'에 '/statistics.jsp' 페이지의 내용이 들어 있습니다.

			// JavaScript 코드를 추출합니다. 예를 들어, script 태그로 둘러싸인 스크립트 내용을 추출할 수 있습니다.
			const scriptRegex = /<script>([\s\S]*?)<\/script>/g;
			const scriptMatches = data.match(scriptRegex);

			if (scriptMatches) {
				scriptMatches.forEach(scriptTag => {
					// 추출한 스크립트를 실행합니다.
					eval(scriptTag.replace(/<\/?script>/g, ''));
				});
			}

			// 나머지 내용을 출력하거나 사용할 수 있습니다.
			document.querySelector('.rightMainOutput').innerHTML = data;
		})
		.catch(error => {
			console.error('데이터를 가져오는 중 오류 발생:', error);
		});

	
}

// 3. 거래내역 경로이동
function goToTradeLog(){
	
	fetch('./tradelog.jsp')
		.then(response => response.text())
		.then(data => {
			document.querySelector('.rightMainOutput').innerHTML = data;
		})
		.catch(error => {
			console.error('데이터를 가져오는 중 오류 발생:', error);
		});

}

// 4. 중개거래소관리 경로이동
function goToemEdiation(){
	
	location.href = "/Ezen_teamB/jsp/manager/emediation.jsp";
	
}



