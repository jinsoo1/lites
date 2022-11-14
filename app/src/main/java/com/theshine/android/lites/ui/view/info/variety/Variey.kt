package com.theshine.android.lites.ui.view.info.variety

import androidx.databinding.ObservableBoolean

data class VarietyData(
    val variety : String
){
    val isChecked : ObservableBoolean = ObservableBoolean(false)
}

val dog_variety = listOf(
"가멜 댄스크 횐제훈드",
"가스콜 생통주아",
"고든 세터",
"곤치 폴스키",
"골든 리트리버",
"그랑 그리퐁 방데앙",
"그랑 바셋 그리퐁 방데앙",
"그랑 블뢰 드 가스코뉴",
"그레이트덴",
"그레이트 스위스 마운틴 독",
"그레이트 앵글로-프렌치 트라이 컬러 하운드",
"그레이트 앵글로-프렌치 화이트 앤드 블랙 하운드",
"그레이트 앵글로-프렌치 화이트 앤드 오렌지 하운드",
"그레이트 피레니즈",
"그레이하운드",
"그리퐁 니베르네",
"그리퐁 블뢰 드 가스코뉴",
"그리퐁 포브 드 브르타뉴",
"그린랜드 독",
"기슈",
"꼬똥 드 툴레아",
"나폴리탄 마스티프",
"노르보텐스피츠",
"노르스크 룬데훈트",
"노르웨이안 부훈트",
"노르웨이언 엘크하운드 그레이",
"노르웨이언 엘크하운드 블랙",
"노리치 테리어",
"노바 스코샤 덕 톨링 리트리버",
"노퍽 테리어",
"뉴펀들랜드",
"닥스훈트",
"단스크-스벤스크 고르스훈드",
"달마시안",
"댄디디먼트 테리어",
"더치 샤펜도스",
"더치 셰퍼드 독",
"더치 스마우스혼트",
"덩커",
"도고 까나리오",
"도고 아르헨티노",
"도그 드 보르도",
"도베르만",
"도사",
"도이체 브라케",
"도이치 스티첼할",
"동경이",
"드레베르",
"드렌츠허 파트레이스혼트",
"디어 하운드",
"라고토 로마뇰로",
"라사 압소",
"라지 먼스터랜더",
"라포니언 허더",
"란트저",
"래브라도 리트리버",
"랭카셔 힐러",
"러시안 블랙 테리어",
"러시안 유러피안 라이카",
"러시안 토이테리어",
"러프 콜리",
"레온베르거",
"레이크랜드 테리어",
"로디지안 리지백",
"로첸",
"롯트와일러",
"루마니안 미오리틱 셰퍼드 독",
"루마니안 카르파티안 셰퍼드 독",
"마렘마 쉽독",
"마스티프",
"마요르카 셰퍼드 독",
"말티즈",
"맨체스터 테리어",
"몬터니그런 마운틴 하운드",
"무디",
"미니어처 불 테리어",
"미니어처 슈나우저",
"미니어처 푸들",
"미니어처 핀셔",
"미디엄 푸들",
"믹스",
"바라크 드 아리에주",
"바라크 드 오베르뉴",
"바라크 생제르맹",
"바바리안 마운틴 센트 하운드",
"바베트",
"바센지",
"바셋 블뢰 드 가스코니",
"바셋 아르테지앙 노르망",
"바셋 포브 드 브르타뉴",
"바셋 하운드",
"버니즈 마운틴 독",
"베들링턴 테리어",
"베르가마스코",
"벨지안 그리펀",
"벨지안 셰퍼드 독",
"보더 콜리",
"보더 테리어",
"보르조이",
"보산스키 오슈트로들라키 고니치-바라크",
"보스 쉽독",
"보스턴 테리어",
"복서",
"볼로네즈",
"볼피노 이탈리아노",
"부비에 데 아르덴",
"부비에 데 플랑드르",
"불 테리어",
"불마스티프",
"브라질리안 가드 도그",
"브라질리언 테리어",
"브라코 이탈리아노",
"브라크 뒤 부르보네",
"브라크 프랑세 가스코뉴 타입",
"브라크 프랑세 피레니언 타입",
"브로홀머",
"브뤼셀 그리펀",
"브리어드",
"브리케 그리퐁 방데앙",
"브리타니",
"블랜 앤 탄 쿤하운드",
"블러드하운드",
"블루 피카르디 스파니엘",
"비글",
"비글 해리어",
"비숑 프리제",
"비어디드 콜리",
"비즐라",
"빌리",
"쁘띠 바셋 그리펀 벤딘",
"쁘띠 브라방송",
"사모예드",
"사부에소 에스파뇰",
"사우스 러시안 오브차카",
"사우스이스턴 유러피언 셰퍼드 독",
"살루키",
"삽살개",
"샤르플라니나츠",
"샤를로스 울프 하운드",
"샤페이",
"서식스 스파니엘",
"세구조 이탈리아노 아 펠로 라소",
"세구조 이탈리아노 아 펠로 포르테",
"세르비안 트라이칼라 하운드",
"세르비안 하운드",
"세인트 버나드",
"센트럴 아시아 셰퍼드 독",
"셰틀랜드 쉽독",
"숄로이츠퀸틀 미니어처",
"숄로이츠퀸틀 스탠다드",
"숄로이츠퀸틀 인터미디어트",
"슈나우저",
"슈바이처 라우프훈트",
"스몰 스위스 하운드",
"스몰란스퇴바레",
"스무스 콜리",
"스무스 폭스 테리어",
"스웨디시 라프훈트",
"스웨디시 발훈트",
"스첼스퇴바레",
"스카이 테리어",
"스코티쉬 테리어",
"스키퍼키",
"스타이리안 콜스 헤어드 하운드",
"스태포드셔 불 테리어",
"스탠다드 푸들",
"스테비훈",
"스페니쉬 그레이하운드",
"스페니쉬 마스티프",
"스페니쉬 워터 독",
"스페인 포인터",
"슬로벤스키 와이어 헤어드 슬로바키안 포인터",
"슬로벤스키 추바치",
"슬로벤스키 코포우",
"슬루기",
"시바",
"시베리언 허스키",
"시츄",
"시코쿠",
"실리햄 테리어",
"아나톨리언 셰퍼드 독",
"아르투아 하운드",
"아리에주아",
"아메리칸 불독",
"아메리칸 스태포드셔 테리어",
"아메리칸 아키타",
"아메리칸 워터 스파니엘",
"아메리칸 코커 스파니엘",
"아메리칸 폭스하운드",
"아이리쉬 글렌 오브 이말 테리어",
"아이리쉬 레드 세터",
"아이리쉬 레드 앤 화이트 세터",
"아이리쉬 소프트코티드 휘튼 테리어",
"아이리쉬 울프하운드",
"아이리쉬 워터 스파니엘",
"아이리쉬 테리어",
"아이슬란딕 쉽 독",
"아자와크",
"아키타",
"아틀라스 마운틴 독",
"아펜첼러 제넨훈트",
"아펜핀셔",
"아프간 하운드",
"알래스카 말라뮤트",
"알파인 닥스브라크",
"앙글로 프랑세 드 프티 베느리",
"에르데이 코포",
"에스트렐라 마운틴 독",
"에어데일 테리어",
"엔틀레부허 제넨훈트",
"오스트레일리안 스텀피 테일 캐틀 독",
"오스트레일리언 셰퍼드",
"오스트레일리언 실키 테리어",
"오스트레일리언 캐틀 독",
"오스트레일리언 캘피",
"오스트레일리언 테리어",
"오스트리안 블랙 앤드 탄 하운드",
"오스트리안 핀셔",
"오이라지어",
"오터 하운드",
"올드 잉글리쉬 쉽독",
"와이마라너",
"와이어 폭스 테리어",
"와이어헤어드 포인팅 그리폰 코르탈스",
"요크셔 테리어",
"우루과이 시마론",
"웨스트 시베리언 라이카",
"웨스트 하이랜드 화이트 테리어",
"웨스트팔리안 닥스브라케",
"웨터훈",
"웰스 스프링거 스파니엘",
"웰쉬 테리어",
"이비전 하운드",
"이스트 시베리언 라이카",
"이스트리안 쇼트 헤어드 하운드",
"이스트리안 와이어 헤어드 하운드",
"이탈리안 스피노네",
"이탈리언 그레이하운드",
"이탈리언 코르소 독",
"잉글리쉬 불독",
"잉글리쉬 세터",
"잉글리쉬 스프링거 스파니엘",
"잉글리쉬 코커 스파니엘",
"잉글리쉬 토이 테리어",
"잉글리쉬 포인터",
"잉글리시 폭스 하운드",
"자이언트 슈나우저",
"잠트훈트",
"재패니즈 스피츠",
"재패니즈 친",
"재패니즈 테리어",
"잭 러셀 테리어",
"저먼 롱헤어드 포인터",
"저먼 바흐텔훈트",
"저먼 셰퍼드 독",
"저먼 숏헤어드 포인팅 독",
"저먼 스피츠 미니어처",
"저먼 스피츠 미디엄",
"저먼 스피츠 자이언트",
"저먼 와이어헤어드 포인터",
"저먼 핀셔",
"저먼 헌팅 테리어",
"진돗개",
"차우차우",
"차이니즈 크레스티드",
"체서피크 베이 리트리버",
"체스키 테리어",
"체스키 포섹",
"체코슬로바키언 울프독",
"치르네코 델레트나",
"치와와",
"카 드 보",
"카디건 웰시 코기",
"카렐리안 베어 독",
"카르스트 셰퍼드 독",
"카스트로 라보레이로 독",
"카이",
"카탈로니안 쉽독",
"캉 드 필라 드 상미겔",
"캐벌리어 킹 찰스 스파니엘",
"컬리 코티드 리트리버",
"케리 블루 테리어",
"케언 테리어",
"케이넌 도그",
"코몬도르",
"코카시안 오브차카",
"쿠바츠",
"쿠이커혼제",
"크로아티안 셰퍼드 독",
"크롬폴란데",
"클라이너 뭔슈테를렌더",
"블럼버 스파니엘",
"키스혼드",
"킹 찰스 스파니엘",
"타이 리지백 독",
"타이 방케우",
"타이완 독",
"타트라 셰퍼드 독",
"토른쟈크",
"토이 맨체스터 테리어",
"토이 푸들",
"티롤러 브라케",
"티베탄 마스티프",
"티베탄 스파니엘",
"티베탄 테리어",
"파라오 하운드",
"파슨 러셀 테리어",
"파피용",
"퍼그",
"페루비안 헤어리스 도그",
"페키니즈",
"펨브록 웰시 코기",
"포덴코 카나리오",
"포뎅구 포르투게스",
"포르셀렌",
"포르투갈 워터 독",
"포르투기즈 쉽독",
"포르투기즈 포인터",
"포메라니안",
"포사박 하운드",
"폴리쉬 로랜드 쉽독",
"폴리시 그레이하운드",
"폴리시 하운드",
"퐁 토드메르 스파니엘",
"푸델포인터",
"푸미",
"풀리",
"풍산개",
"프렌치 불독",
"프렌치 스파니엘",
"프렌치 트라이 컬러 하운드",
"프렌치 화이트 앤 오렌지 하운드",
"프렌치 화이트 앤드 블랙 하운드",
"프와트방",
"프티 블뢰 드 가스코뉴",
"플랫 코티드 리트리버",
"피니쉬 하운드",
"피니시 스피츠",
"피레니언 마스티프",
"피레니언 쉽독",
"피레니언 쉽독 롱헤어",
"피카르디 쉽독",
"피카르디 스파니엘",
"필드 스파니엘",
"하노베리언 하운드",
"하밀톤스퇴바레",
"하페이루 두 알렌테주",
"할덴 하운드",
"해리어",
"허배너스",
"헝가리안 그레이하운드",
"헝가리언 와이어 헤어드 비즐라",
"헬레닉 하운드",
"호파와트",
"훗카이도",
"화이트 스위스 셰퍼드",
"휘핏",
"히겐훈드"
)


val cat_variety = listOf(
    "나폴레옹",
    "나폴레옹 롱헤어",
    "네벨룽",
    "노르웨이 숲",
    "데본 렉스",
    "돈스코이",
    "라이코이",
    "라팜",
    "라팜 숏헤어",
    "랙돌",
    "러시안 블루",
    "맹크스",
    "먼치킨",
    "먼치킨 롱헤어",
    "메인 쿤",
    "메인 쿤 폴리닥틸",
    "믹스",
    "민스킨",
    "발리니즈",
    "버만",
    "버미즈",
    "버밀라",
    "버밀라 롱헤어",
    "벵갈",
    "벵갈 롱헤어",
    "봄베이",
    "브리티쉬 롱헤어",
    "브리티쉬 숏헤어",
    "사바나",
    "샤르트뢰",
    "샴 (샤미즈)",
    "세렝게티",
    "셀커크 렉스",
    "셀커크 렉스 롱헤어",
    "소말리",
    "스노우슈",
    "스코티시 스트레이트",
    "스코티시 스트레이트 롱헤어",
    "스코티시 폴드",
    "스코티시 폴드 롱헤어",
    "스핑크스",
    "시베리안",
    "싱가푸라",
    "아메리칸 밥테일",
    "아메리칸 밥테일 숏헤어",
    "아메리칸 와이어헤어",
    "아메리칸 컬",
    "아메리칸 컬 롱헤어",
    "아비시니안",
    "엑조틱 숏헤어",
    "오리엔탈 롱헤어",
    "오리엔탈 숏헤어",
    "오스트레일리안 미스트",
    "오시캣",
    "이집션 마우",
    "재패니즈 밥테일",
    "재패니즈 밥테일 롱헤어",
    "쵸시",
    "카오마니",
    "코니시 렉스",
    "코랏",
    "코리안 숏헤어",
    "쿠리리안 밥테일",
    "쿠리리안 밥테일 롱헤어",
    "키프로스",
    "킴릭",
    "타이",
    "터키시 반",
    "터키시 앙고라",
    "테네시 렉스",
    "토이거",
    "토이밥",
    "톤키니즈",
    "페르시안",
    "피터볼드",
    "픽시밥",
    "픽시밥 롱헤어",
    "하바나",
    "하이랜더",
    "하이랜더 숏헤어",
    "히말라얀"
)

