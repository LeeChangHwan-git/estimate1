# @TODO
1. 견적요청시 id를 입력값으로 받고 있는데, 별로 좋아보이지 않음
- spring security 적용 or HttpSession 이용할 것
2. 에러는 모두 200으로 주고 메시지로 식별한다.
3. 12시마다 견적건수 초기화하는 배치
4. 입력값, 에러스택은 파일로 떨군다


# DATA 채우기
-- Users
INSERT INTO users (user_id, password, name, mobile, email, user_type) VALUES
('client1', 'pass123', '김고객', '010-1111-1111', 'client1@test.com', 'CLIENT'),
('client2', 'pass123', '이고객', '010-2222-2222', 'client2@test.com', 'CLIENT'),
('expert1', 'pass123', '박전문가', '010-3333-3333', 'expert1@test.com', 'EXPERT'),
('expert2', 'pass123', '최전문가', '010-4444-4444', 'expert2@test.com', 'EXPERT'),
('client3', 'pass123', '정고객', '010-5555-5555', 'client3@test.com', 'CLIENT'),
('expert3', 'pass123', '강전문가', '010-6666-6666', 'expert3@test.com', 'EXPERT'),
('client4', 'pass123', '윤고객', '010-7777-7777', 'client4@test.com', 'CLIENT'),
('expert4', 'pass123', '임전문가', '010-8888-8888', 'expert4@test.com', 'EXPERT'),
('client5', 'pass123', '한고객', '010-9999-9999', 'client5@test.com', 'CLIENT'),
('expert5', 'pass123', '신전문가', '010-0000-0000', 'expert5@test.com', 'EXPERT');

-- Expert Info
INSERT INTO expert_info (expert_id) VALUES
('expert1'),
('expert2'),
('expert3'),
('expert4'),
('expert5'),
('expert6'),
('expert7'),
('expert8'),
('expert9'),
('expert10');

-- Expert Categories
INSERT INTO expert_categories (user_id, category) VALUES
('expert1', 'FACILITY'),
('expert1', 'ELECTRICAL'),
('expert2', 'CLEAN'),
('expert2', 'FACILITY'),
('expert3', 'ELECTRICAL'),
('expert3', 'CLEAN'),
('expert4', 'FACILITY'),
('expert4', 'ELECTRICAL'),
('expert5', 'CLEAN'),
('expert5', 'FACILITY');

-- Projects
INSERT INTO projects (user_id, project_type, category, status, desired_date, city, district, address, square_footage, budget, startup_type) VALUES
('client1', 'UNMANNED_CAFE', 'FACILITY', 'ESTIMATE_REQ', '2024-03-01', '서울', '강남구', '테헤란로 123', '100', '5000만원', 'SELF'),
('client1', 'UNMANNED_CAFE', 'ELECTRICAL', 'ESTIMATE_REQ', '2024-03-01', '서울', '강남구', '테헤란로 123', '100', '5000만원', 'SELF'),
('client2', 'UNMANNED_ICECREAM', 'CLEAN', 'ESTIMATE_REQ', '2024-04-01', '서울', '서초구', '서초대로 456', '80', '3000만원', 'FRANCHISE'),
('client2', 'UNMANNED_ICECREAM', 'FACILITY', 'ESTIMATE_DONE', '2024-04-01', '서울', '서초구', '서초대로 456', '80', '3000만원', 'FRANCHISE'),
('client3', 'OTHER', 'ELECTRICAL', 'ESTIMATE_REQ', '2024-05-01', '인천', '부평구', '부평대로 789', '120', '7000만원', 'TRANSFER_STARTUP'),
('client3', 'OTHER', 'CLEAN', 'DETAIL_REQ', '2024-05-01', '인천', '부평구', '부평대로 789', '120', '7000만원', 'TRANSFER_STARTUP'),
('client4', 'UNMANNED_CAFE', 'FACILITY', 'ESTIMATE_REQ', '2024-06-01', '부산', '해운대구', '해운대로 321', '90', '4000만원', 'SELF'),
('client4', 'UNMANNED_CAFE', 'ELECTRICAL', 'COMPLETED', '2024-06-01', '부산', '해운대구', '해운대로 321', '90', '4000만원', 'SELF'),
('client5', 'UNMANNED_ICECREAM', 'CLEAN', 'ESTIMATE_REQ', '2024-07-01', '대구', '수성구', '수성로 654', '70', '2500만원', 'FRANCHISE'),
('client5', 'UNMANNED_ICECREAM', 'FACILITY', 'DETAIL_DONE', '2024-07-01', '대구', '수성구', '수성로 654', '70', '2500만원', 'FRANCHISE');