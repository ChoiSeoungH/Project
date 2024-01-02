# _Main



# Buildings
# Files
>  1. gameData.txt

  돈과 플레이어 유닛들 그리고 아이템을 저장한 데이터
>  2. FileManager
	  
	[1] loadFile
     gameData.txt 에 저장된 데이터를 읽어와서 UnitManager를 통해 

	[2] saveFile
	  
# Item
# Manager
# PlayerClass
  플레이어 유닛들의 직업을 정해서 무기와 스킬에 제한을 둘수있게한 인터페이스들의 모음
  >  1. Entertainer : 예능인 
>    2. Fighter : 격투가
>    3. Healer : 힐러
>    4. Hero : 용사
>    5. Magician : 마법사
>    6. Thief : 도적
# SKill
# Stage
> 1.
> 2.
> 3.
> 4.
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
	
