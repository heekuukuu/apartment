<%@ page contentType="text/html;charset=UTF-8" language="java" %>
// /apartment/home.js 파일에 있는 함수
function goHome() {
    window.location.href = '/user/index.jsp';  // 이동할 홈 페이지의 경로를 지정하세요
}



// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
    var el = document.createElement('li'),
        itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                  '<div class="info">' +
                  '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>';
    }

    itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';


}
// 검색 결과 항목이 클릭될 때 호출되는 함수입니다
function handleListItemClick(name, address) {
     if (!address) {
         alert('유효한 주소를 입력해주세요.');
         return;
     }

     if (confirm('해당 위치를 저장하시겠습니까?')) {
         saveLocation(name, address);  // 위치 저장 함수 호출
     }

 }






