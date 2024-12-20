-- Câu 1
select* from tblEmployee
SELECT e.empSSN, e.empName, d.depNum, d.depName
FROM tblEmployee e
JOIN tblDepartment d ON e.depNum = d.depNum
WHERE d.depNum = 5;

--câu 2
SELECT p.proName, p.proNum, p.depNum, d.depName
FROM tblProject p
JOIN tblDepartment d ON p.depNum = d.depNum
WHERE d.depNum = 5;

select*from tblProject
--câu 3
select p.proNum, p.proName, d.depName
from tblProject p
join tblDepartment d on p.depNum = d.depNum
where p.proName ='ProjectB';

--câu 4
select e.empSSN, s.empName
from tblEmployee e 
join tblEmployee s on s.supervisorSSN = e.empSSN
where e.empName = 'Mai Duy An'

-- câu 5
SELECT e.empSSN, e.empName
FROM tblEmployee e
JOIN tblEmployee s ON e.empSSN = s.supervisorSSN
Where s.empName = N'Mai Duy An'

-- cau 1 
select * from tblEmployee
select d.depName, d.depNum, avg(empSalary) as 'Luong trung bình'
from tblEmployee e join tblDepartment d on e.depNum=d.depNum
group by d.depName, d.depNum
having d.depNum=2


--cau 2
SELECT p.depNum, p.proName, p.proNum
FROM tblProject p
Join tblDepartment d  ON d.depNum = p.depNum 
where d.depName = N'Phòng Nghiên cứu và phát triển hiện'


-- cau 3
SELECT d.depNum, d.depName, p.proName
FROM tblDepartment d
JOIN tblProject p ON d.depNum = p.depNum
Where p.proName = N'ProjectB'

-- cau 4
SELECT s.empSSN, s.empName
FROM tblEmployee e
JOIN tblEmployee s ON e.empSSN = s.supervisorSSN
Where e.empName = N'Mai Duy An'

-- cau 5
SELECT e.empSSN, e.empName
FROM tblEmployee e
JOIN tblEmployee s ON e.empSSN = s.supervisorSSN
Where s.empName = N'Mai Duy An'

-- cau 6
select l.locNum, l.locName
from tblLocation l
JOIN tblProject p ON p.locNum = l.locNum
Where p.proName = N'ProjectA'

-- cau 7
select p.proNum, p.proName
from tblProject p
JOIN tblLocation l ON p.locNum = l.locNum
Where l.locName = N'TP Hồ Chí Minh'

-- cau 8
select d.depName, d.depBirthdate, e.empName
from tblDependent d join tblEmployee e on d.empSSN=e.empSSN
where DATEDIFF(YEAR, d.depBirthdate, GETDATE()) > 18

-- cau 9
select d.depName, d.depBirthdate, e.empName
from tblDependent d
JOIN tblEmployee e ON e.empSSN = d.empSSN
Where d.depSex = N'M'

-- cau 10
select d.depNum, d.depName, l.locName
from tblDepartment d
JOIN tblProject p ON p.depNum = d.depNum
JOIN tblLocation l ON p.locNum = l.locNum
Where d.depName = N'Phòng Dịch vụ chăm sóc khách hàng'

--cau11
select p.proNum, p.proName, d.depName
from tblProject p 
JOIN tblDepartment d ON p.depNum = d.depNum
JOIN tblLocation l ON p.locNum = l.locNum
Where l.locName = N'TP Hồ Chí Minh'

-- cau12 
select e.empName, dpd.depName, dpd.depRelationship
from tblDependent dpd
JOIN tblEmployee e ON e.empSSN = dpd.empSSN
JOIN tblDepartment d ON d.depNum = e.depNum
Where dpd.depSex=N'F'

-- cau 13
select e.empName, dpd.depName, dpd.depRelationship
from tblDependent dpd
JOIN tblEmployee e ON e.empSSN = dpd.empSSN
JOIN tblDepartment d ON e.depNum = d.depNum
Where d.depName = N'Phòng Nghiên cứu và phát triển'

-- cau 14
select d.depSex, Count(*) as count
from tblDependent d
group by d.depSex

-- cau 15
select d.depRelationship, COUNT(*) as counts
from tblDependent d
group by d.depRelationship

--cau 16
select d.depNum, d.depName ,COUNT(dpd.empSSN) as dependentCount
from tblDepartment d
JOIN tblEmployee e ON e.depNum = d.depNum
JOIN tblDependent dpd ON dpd.empSSN = e.empSSN
group by d.depNum, d.depName

-- cau17
select TOP 1 d.depNum, d.depName ,COUNT(dpd.empSSN) as dependentCount
from tblDepartment d
JOIN tblEmployee e ON e.depNum = d.depNum
JOIN tblDependent dpd ON dpd.empSSN = e.empSSN
group by d.depNum, d.depName
ORDER BY dependentCount ASC

-- cau 18
Select Top 1 d.depNum, d.depName, COUNT(dpd.empSSN) as dependentCount
from tblDepartment d
JOIN tblEmployee e ON e.depNum = d.depNum
JOIN tblDependent dpd ON dpd.empSSN = e.empSSN
group by d.depNum, d.depName
order by dependentCount DESC

-- cau 19
select e.empSSN, e.empName, d.depName, sum(w.workHours) as totalHour
from tblEmployee e
JOIN tblDepartment d ON d.depNum = e.depNum
JOIN tblWorksOn w ON e.empSSN = w.empSSN
group by  e.empSSN, e.empName, d.depName

-- cau 20
select d.depNum, d.depName, sum(w.workHours)
from tblDepartment d
JOIN tblEmployee e ON e.depNum = d.depNum
JOIN tblWorksOn w ON w.empSSN = e.empSSN
group by d.depNum, d.depName

-- cau 21
select top 1 e.depNum, e.empName, sum(w.workHours) as totalHour
from tblEmployee e
JOIN tblWorksOn w ON w.empSSN = e.empSSN
group by e.depNum, e.empName 
order by totalHour asc

--cau 22
select top 1 e.empSSN, e.empName, sum(w.workHours) as totalHour
from tblEmployee e
JOIN tblWorksOn w ON w.empSSN = e.empSSN
group by e.empSSN, e.empName
order by totalHour desc

--cau 23
select e.empSSN, e.empName, d.depName
from tblEmployee e
JOIN tblWorksOn w ON w.empSSN = e.empSSN
JOIN tblDepartment d ON d.depNum = e.depNum
group by e.empSSN, e.empName, d.depName
having count(w.empSSN) = 1

-- cau 24
select e.empSSN, e.empName, d.depName
from tblEmployee e
JOIN tblDepartment d ON d.depNum = e.depNum
JOIN tblWorksOn w ON w.empSSN = e.empSSN
group by e.empSSN, e.empName, d.depName
having count(w.empSSN) = 2

-- cau 25
select e.empSSN, e.empName, d.depName
from tblEmployee e
JOIN tblDepartment d ON d.depNum = e.depNum
JOIN tblWorksOn w ON w.empSSN = e.empSSN
group by e.empSSN, e.empName, d.depName
having count(w.empSSN) >= 2

-- cau 26
select p.proNum, p.proName, sum(e.depNum) as numbers
from tblProject p
JOIN tblEmployee e ON e.depNum = p.depNum
group by p.proNum, p.proName

-- cau 27
select p.proNum, p.proName, sum(w.workHours) as totalHours
from tblProject p
JOIN tblWorksOn w ON w.proNum = p.proNum
group by p.proNum, p.proName

-- cau 28
select top 1 p.proNum, p.proName, count(w.proNum) as totalMembers
from tblProject p
JOIN tblWorksOn w ON w.proNum = p.proNum
group by p.proNum, p.proName
order by totalMembers asc

-- cau 29
select top 1 p.proNum, p.proName, count(w.proNum) as totalMembers
from tblProject p
JOIN tblWorksOn w ON w.proNum = p.proNum
group by p.proNum, p.proName
order by totalMembers desc

-- cau 30
select top 1 p.proNum, p.proName, sum(w.workHours) as totalHours
from tblProject p
JOIN tblWorksOn w ON w.proNum = p.proNum
group by p.proNum, p.proName
order by totalHours asc

-- cau 31
select top 1 p.proNum, p.proName, sum(w.workHours) as totalHours
from tblProject p
JOIN tblWorksOn w ON w.proNum = p.proNum
group by p.proNum, p.proName
order by totalHours desc

-- cau 32
select l.locName, count(d.depNum) as countdepart
from tblLocation l
JOIN tblDepLocation d ON d.locNum = l.locNum
JOIN tblDepartment dpt ON dpt.depNum = d.depNum
group by l.locName

-- cau 33
select * from tblDepLocation
select s.depNum, count (d.locNum) as 'số lượng chỗ làm', s.depName
from tblDepLocation d
join tblDepLocation on d.depNum= d.depNum
JOIN tblDepartment s ON s.depNum = d.depNum
group by d.locNum