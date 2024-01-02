# _Main
게임을 실행시키는 클래스

# Building
>  1. Church
	[1] 기도하기(저장)
	FileManeger 클래스를 통한 저장
	[2] 되살리기
	죽은 멤버들을 출력 없을시 다른 메뉴 출력
	부활시킬 멤버의 이름 입력 예외확인후 부활
	[3] 스킬리셋
	<미구현> 스킬포인트 1당 20골드로 스킬 초기화
>  2. Inn
	[1] 숙박
        하루가 지나며 사망하지 않은 모든 파티원의 체력을 회복
	[2] 휴식
        지정한 시간까지 시간이 흐르고 사망하지 않은 모든 파티원의 체력을 회복
        돈이 부족할시 여관 이용 불가
>  3. Shop
        지정해둔 아이템 심볼로 종류를 구분하여 구매및 판매
        [1] 구매
	    1)무기
            2)갑옷
            3)반지
	[2] 판매
	절반가격으로 판매
 
	
# Files
>  1. gameData.txt

  돈과 플레이어 유닛들 그리고 아이템을 저장한 데이터
>  2. FileManager
	  
	[1] loadFile
        gameData.txt 에 저장된 데이터를 읽어와서 UnitManager를 통해 플레이어 리스트에 저장

	[2] saveFile
        UnitManager를 통해 플레이어 리스트를 데이터로 읽어와 gameData.txt로 저장
	  
# Item
 >  1. ItemSymbol 
   무기와 갑옷 반지의 심볼을 유니코드로 저장한 Enum클래스
>   2. Item 
   아이템의 종류 이름 가격 파워를 저장하는 VO
>   3. Inventory 
   [1]착용
   아이템 리스트가 비어있으면 착용 불가
   파티원 선택후 아이템 착용 -> 착용하고있던 아이템은추가 착용한 아이템은 리스트에서 제거
   [2]해제
   파티원의 아이템이 비어있으면 해제 불가
   아이템 해제 -> 착용하고있던 아이템을 아이템 리스트에 추가
# Manager
> 1. GameManager
     
> 2. UnitManager
# Map
> 1. GameMap
     모든 맵들의 인터페이스 크기는 7x7
     초기화,맵출력,플레이어이동
> 2. MapSymbol
     맵상의 오브젝트들의 심볼을 유니코드로 지정 -> 플레이어의 경우 파티원의 맨앞에있는 멤버의 유니코드가 나옴
> 3. Town(이시마을)
> 4. Delkadar(델카다르)
# PlayerClass
  플레이어 유닛들의 직업을 정해서 무기와 스킬에 제한을 둘수있게한 인터페이스들의 모음
  >  1. Entertainer : 예능인 
>    2. Fighter : 격투가
>    3. Healer : 힐러
>    4. Hero : 용사
>    5. Magician : 마법사
>    6. Thief : 도적
# SKill
 >  1. Skill 
>    2. Acrobatic 
     예능인 전용 스킬 -> 불뿜기 2~3의 데미지로 1~3회 공격 
>    3. Dagger 
     도적 전용 스킬 -> 어쌔신 어택 20%확률로 적 즉사
>    4. Hand 
     격투가 전용 스킬 -> 일섬 찌르기 40%확률로 적 3배 데미지
>    5. Heal 
     힐러 전용 스킬 -> 호이미 아군 파티원의 체력을 10회복
>    6. Spell
     마법사 전용 스킬 -> 메라 적하나에게 10의 불속성 마법데미지
>    7. Sword
     용사 전용 스킬 -> 화염베기 적 하나에게 1.2배만큼의 데미지
# Stage
> 1. Stage 
     스테이지들의 인터페이스 
> 2. StageTitle
     첫 시작화면 아무버튼이나 입력하여 로비로 이동
> 3. StageLobby
    로비
> 4. StagePlay
    직접 플레이하게되는 스테이지
> 5. StageBattle
    전투를 위한 스테이지
# Unit
>  1. Unit
    유닛들의 최고 조상 추상 클래스
    체력과 공격력, 방어력, 레벨, 경험치, 이름, 사망여부등을 저장한다.

    attack 메서드를 정의하였고 공격시 데미지는 내 공격력에 상대방의 방어력을 빼준값이다.
    
    경험치를 일정 이상 획득시 레벌업을 하며 레벨업당 체력+2 2레벨업당 공격력+1 3레벨업당 방어력+1의 스텟을 얻으며
    필요경험치량도 2씩 증가한다.
    
>  2. Player
    플레이어 유닛들의 조상 클래스
    유닛에 추가로 스킬과 파티여부 아이템을 설정해준다.
    [1]PlayerHero
      용사 직업의 무기와 스킬을 사용 할 수 있다.
    [2]PlayerKamui
      도적 직업의 무기와 스킬을 사용 할 수 있다.
    [3]PlayerMartina
      격투가 직업의 무기와 스킬을 사용 할 수 있다.
    [4]PlayerRow
      격투가, 마법사, 힐러 직업의 무기와 스킬을 사용 할 수 있다.
    [5]PlayerSenya
      마법사, 힐러 직업의 무기와 스킬을 사용 할 수 있다.
    [6]PlayerSilvia
      예능인 직업의 무기와 스킬을 사용 할 수 있다.
    [7]PlayerVeronica
      마법사 직업의 무기와 스킬을 사용 할 수 있다.
>  3. Monster
    몬스터를 구분하기 위한 인터페이스
    [1]Dracky
      드라키 : 흡혈 공격 -> 가한 데미지의 절반만큼 체력을 회복한다.
    [2]Needler
      몽실키 : 강타 -> 1.5배의 데미지를 준다.
    [3]Slime
      슬라임 : 지원요청 -> 20프로 확률로 새로운 슬라임을 부른다.
  
# Util
>  1. getValue
    String과 int값을 실행시 에러가 발생하지않도록 예외처리하여 입력받는다.
>  2. setDelay
    입력해준 시간만큼 쓰레드를 임시중단하여 출력값에 딜레이를 만들어준다.

# 미구현
  직업별 무기와 무기교체, 시간 설정, 건물 내부 , 추가적인 Map, 직업별 추가스킬들과 스킬포인트를 사용한 스킬분배,보스 몬스터,NPC
  몬스터들의 랜덤 움직임
	
