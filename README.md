# BookMvvm

안녕하세요 센드버드 지원자 김성재 입니다.

전달해 주신 과제를 개발하여 업로드 하였습니다. 확인 부탁드립니다.

1.[나의 서재 앱] 구현
- [New],[Search],[Bookmark],[History] 4개의 탭을 가진다 (구현완료)
- 서적 리스트 중 하나를 선택하면 세부 정보를 보여주는 [Detail Book]화면을 보여준다 (구현완료)
- [2. 세부 구현 내용]을 보고 [New], [Detail Book], [Search], [Bookmark], [History] 순서로 구현한다. (구현완료)
- [추가 과제] : MVP, MVVM 등의 Pattern 을 활용한 구조로 설계한다. (MVVM패턴을 일부 이용하여 구현 완료)
- [추가 과제] : Unit test를 작성한다. (구현완료)

2. 세부 구현 내용
1) [New] : 새로운 서적 리스트를 보여준다. 
- JSON으로 넘어오는 모든 정보(이미지 포함)를 보여주어야 한다. (구현완료)
- 새로운 서적 리스트의 결과는 항상 정해진 수의 갯수만 전달 된다. (구현완료)

2) [Detail Book] : 서적 리스트 중 선택된 서적의 상세 정보를 보여준다.
- JSON으로 넘어오는 모든 정보(이미지 포함)를 보여주어야 한다. (구현완료)
- 북마크 버튼을 누르면 [Bookmark] 리스트에 추가되고, 다시 선택하면 [Bookmark]리스트에서 삭제된다. (구현완료)
- 북마크 기능은 앱 실행 중에만 동작하면 된다. (즉, 앱 재실행시 기존 정보가 저장되어 있을 필요는 없다.) (구현완료)
- [추가 과제] 사용자가 메모를 남길 수 있도록 한다. (구현완료)

3) [Search] : 특정 키워드에 대한 서적 검색 정보를 보여준다.
- 특정 키워드를 입력받을 수 있도록 한다. (구현완료)
- JSON으로 넘어오는 모든 정보 (이미지 포함)를 보여주어야 한다. (구현완료)
- API 요청에 대한 응답 결과는 페이지 별로 구분된다.  (구현완료)
- 그라나 검색 결과는 스크롤링을 사용하여 페이지 구분을 없앤다. (구현완료)
- 즉, 검색화면은 스크롤을 사용하여 부드럽게 모든 결과를 볼 수 있어야 한다. (구현완료)
- [추가 과제] 입력했던 검색어 히스토리를 만들어 사용자가 바로 선택할 수 있도록 한다. (미구현)
- [추가 과제] 검색된 데이터를 캐시 처리하여 결과를 빠르게 먼저 볼 수 있도록 한다. (미구현)

4) [Bookmark] : [Detail Book] 화면에서 선택된 북마크들을 모아서 리스트로 보여준다.
- [New]나 [Search]에서 보여준 모든 정보(이미지 포함)를 보여주어야 한다. (구현완료)
- [추가 과제] 정렬 방식을 선택할 수 있다. (미구현)
- [추가 과제] 리스트를 편집할 수 있다. (구현완료)

5) [History] : 상세 정보를 열람한 모든 서적들의 리스트를 보여준다. 
- [New]나 [Search]에서 보여준 모든 정보(이미지 포함)를 보여주어야 한다. (구현완료)
- [추가 과제] 리스트를 편집할 수 있다. (구현완료)


