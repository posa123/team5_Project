drop database if exists usedtrade;
create database usedtrade;
use usedtrade;

# 회원정보 테이블
drop table if exists memberlist;
create table memberlist(
    mno int auto_increment,            
    mname varchar(30) not null,         -- 이름
    msno varchar(20) unique not null,   -- 주민번호
    mphone varchar(20) unique not null,   -- 전화번호
    memail varchar(50) unique not null,   -- 이메일
    madress varchar(100) not null,      -- 주소
    mid varchar(20) unique not null,   -- 아이디
    mpwd varchar(20) not null,         -- 비빌번호
    mlevel int,                     -- 칭호
    mpoint int,                     -- 포인트
    primary key(mno)
);

# 회원 샘플코드
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('관리자', '111111-1111111', '111-1111-1111', 'aaaaaa@aaa.com','admin', 'admin', '123123', 1, 0);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('이천수', '990811-1020333', '010-1111-2222', 'aaabbb@naver.com','안산시 상록구 부곡동', 'abc1234', '123123', 1, 30000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('장덕수', '890711-1020233', '010-1311-2222', 'aacbbb@naver.com','서울시 강남구 청담동', 'abb1234', '123123', 1, 30000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('장예리', '960815-1020113', '010-4441-2222', 'aadbbb@naver.com','서울시 관악구 신림동', 'abd1234', '123123', 2, 30000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('이은서', '971111-1020433', '010-1121-2122', 'aafbbb@naver.com','서울시 관악구 봉천동', 'abf1234', '123123', 1, 40000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('김건우', '930121-1020533', '010-1411-4222', 'aagbbb@naver.com','안산시 상록구 일동', 'abg1234', '123123', 3, 30000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('양준수', '950205-1020633', '010-1511-5222', 'aahbbb@naver.com','수원시 팔달구 인계동', 'abh1234', '123123', 1, 20000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('김수빈', '990528-2020333', '010-1611-2622', 'aaibbb@naver.com','서울시 강남구 신사동', 'abi1234', '123123', 2, 50000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('박정우', '910112-1021233', '010-1711-7222', 'aajbbb@naver.com','안산시 단원구 선부동', 'abj1234', '123123', 1, 20000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('이다은', '870509-2024433', '010-1811-2282', 'aakbbb@naver.com','서울시 관악구 신림동', 'abk1234', '123123', 1, 30000);
insert into memberlist(mname, msno, mphone, memail, madress, mid, mpwd, mlevel, mpoint) 
values('박다정', '890102-2123333', '010-1919-2222', 'ababbb@naver.com','서울시 강남구 청담동', 'bbb1234', '123123', 2, 10000);


# 카테고리
drop table if exists category;
create table category(
	cno int auto_increment,
    cname varchar(30) not null,      -- 카테고리명
    primary key(cno)
);

# 카테고리 샘플코드
insert into category(cname) values('공지사항');
insert into category(cname) values('건의사항');

# 게시판
drop table if exists board;
create table board(
   bno int auto_increment,
    btitle longtext not null,      -- 제목
    bcontent longtext not null,      -- 작성내용
    bdate datetime default now(),   -- 작성일
    bfile longtext,               -- 첨부파일
    mno int,                  -- 작성자
    cno int,                  -- 카테고리
    primary key(bno),
    foreign key( mno ) references memberlist ( mno ),
   foreign key( cno ) references category ( cno )
);

# 게시판 샘플코드
insert into board(btitle, bcontent, bfile, mno, cno)
values('가지가지 공지사항1', '공지사항1', 'default.jpg', 1, 1);
insert into board(btitle, bcontent, bfile, mno, cno)
values('가지가지 공지사항2', '공지사항2', 'default.jpg', 1, 1);
insert into board(btitle, bcontent, bfile, mno, cno)
values('가지가지 공지사항3', '공지사항3', 'default.jpg', 1, 1);
insert into board(btitle, bcontent, bfile, mno, cno)
values('건의사항1', '건의사항1', 'default.jpg', 2, 2);
insert into board(btitle, bcontent, bfile, mno, cno)
values('건의사항2', '건의사항2', 'default.jpg', 3, 2);
insert into board(btitle, bcontent, bfile, mno, cno)
values('건의사항3', '건의사항3', 'default.jpg', 4, 2);
insert into board(btitle, bcontent, bfile, mno, cno)
values('건의사항4', '건의사항4', 'default.jpg', 4, 2);
insert into board(btitle, bcontent, bfile, mno, cno)
values('가지가지 공지사항4', '공지사항4', 'default.jpg', 1, 1);
insert into board(btitle, bcontent, bfile, mno, cno)
values('건의사항5', '건의사항5', 'default.jpg', 10, 2);
insert into board(btitle, bcontent, bfile, mno, cno)
values('건의사항6', '건의사항6', 'default.jpg', 5, 2);
insert into board(btitle, bcontent, bfile, mno, cno)
values('건의사항7', '건의사항7', 'default.jpg', 7, 2);


# 중개거래소
drop table if exists emediation;
create table emediation(
	eno int auto_increment,   
	ename varchar(50) not null,      -- 중개거래소분류
    eadress varchar(100) not null,   -- 중개거래소 주소
	elat varchar(30) not null,      -- 위도
    elng varchar(30) not null,      -- 경도
    primary key(eno)
);

# 중개거래소 샘플코드
insert into emediation(ename, eadress, elat, elng)
values('미니스톱', '강원도 강릉시 주문진읍 주문로 58-1','128.825870','37.8890791' );
insert into emediation(ename, adress, elat, elng)
values('CU', '강원도 태백시 연지로 38','128.990924','37.1765133' );
insert into emediation(ename, adress, elat, elng)
values('미니스톱', '강원도 원주시 문막읍 문막시장1길 54	','127.815872','37.3055613');


# 대분류 물품 카테고리
drop table if exists umaincategory;
create table umaincategory(
   uno int auto_increment,
    uname varchar(50) not null,      -- 대분류 카테고리명
    primary key(uno)
);

# 소분류 물품 카테고리
drop table if exists dsubcategory;
create table dsubcategory(
   dno int auto_increment,
    dname varchar(50) not null,      -- 소분류 카테고리명
    uno int not null,            -- 대분류 카테고리
    primary key(dno),
    foreign key( uno ) references umaincategory ( uno )
);

# 판매물품정보
drop table if exists itemsinfo;
create table itemsinfo(
   ino int auto_increment,
   iprice int not null,               -- 판매가격
    mno int not null,                  -- 판매자
    ititle varchar(50) not null,         -- 판매제목
    icontent longtext not null,            -- 판매내용
    itrade tinyint not null,            -- 거래방식( 1 배송, 2 대면거래, 3 중개거래 )
    itradeplace varchar(100) not null,      -- 거래장소
    idate datetime default now(),         -- 판매물품 등록일시
    eno int,                        -- 중개소 번호( 중개소거래를 이용할 시 )
    iestate int not null,               -- 거래상태( 0 판매중 1 판매완료 )
    dno int not null,                  -- 소분류 뭂품 카테고리
    isafepayment tinyint not null,         -- 안전결제사용여부( 0 미사용 1 사용 )
    keepstate int not null,               -- 중개거래소 물품 보관 여부
    primary key(ino),
    foreign key( mno ) references memberlist ( mno ),
    foreign key( eno ) references emediation ( eno ),
    foreign key( dno ) references dsubcategory ( dno )
);

# 위/경도 (대면거래)
drop table if exists dpoint;
create table dpoint(
   dno int auto_increment,
   dlat varchar(30) not null,   -- 위도
    dlng varchar(30) not null,   -- 경도
    ino int,
    primary key(dno),
    foreign key(ino) references itemsinfo(ino)
);

# 판매물품 이미지
drop table if exists pimg;
create table pimg(
   pno int auto_increment,
    pimg longtext,         -- 이미지
    ino int,            -- 판매물품
    primary key(pno),
    foreign key(ino) references itemsinfo(ino)
);

# 찜 (판매중인 물품만 해당)
drop table if exists watchitem;
create table watchitem(
   wno int auto_increment,
    mno int not null,               -- 찜한 이용자
    ino int not null,               -- 찜한 물품
    primary key(wno),
   foreign key( mno ) references memberlist ( mno ),
    foreign key( ino ) references itemsinfo ( ino )
);

# 거래내역 (거래완료된 물품만 해당)
drop table if exists tradelog;
create table tradelog(
   tno int auto_increment,
    buyer int not null,            -- 구매자
    tradedate datetime not null,   -- 거래일시
    ino int not null,            -- 거래물품
    primary key(tno),
    foreign key( buyer ) references memberlist ( mno ),
   foreign key( ino ) references itemsinfo ( ino )
);

# 채팅
drop table if exists jchatting;
create table jchatting(
   jno int auto_increment,            
    Caller int,                     -- 발신자
   receiver int,                  -- 수신자
   jcontent longtext,               -- 내용
    jchatdate datetime default now(),   -- 채팅일시
    ino int,                     -- 판매물품
    primary key(jno),
    foreign key( ino ) references itemsinfo ( ino )
);
