-- C�u 1
select* from tblEmployee
SELECT e.empSSN, e.empName, d.depNum, d.depName
FROM tblEmployee e
JOIN tblDepartment d ON e.depNum = d.depNum
WHERE d.depNum = 5;

--c�u 2
SELECT p.proName, p.proNum, p.depNum, d.depName
FROM tblProject p
JOIN tblDepartment d ON p.depNum = d.depNum
WHERE d.depNum = 5;

select*from tblProject
--c�u 3
select p.proNum, p.proName, d.depName
from tblProject p
join tblDepartment d on p.depNum = d.depNum
where p.proName ='ProjectB';

--c�u 4
select e.empSSN, s.empName
from tblEmployee e 
join tblEmployee s on s.supervisorSSN = e.empSSN
where e.empName = 'Mai Duy An'


