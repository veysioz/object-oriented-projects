from classes import *
import re

class_list = ClassList()
output = Output()

students = []
no = 1
for i in range(class_list.sheet.col(0).__len__()):
    row = class_list.sheet.row(i)
    if re.match(r'^-?\d+(?:\.\d+)?$', str(row.__getitem__(1).value)):
        students.append(Student(no, row.__getitem__(2).value, row.__getitem__(4).value, row.__getitem__(7).value))
        no += 1

row_num = 0
output.write_title(row_num, 0, "NO")
output.write_title(row_num, 1, "ID")
output.write_title(row_num, 2, "Name")
output.write_title(row_num, 3, "Surname")
for s in students:
    row_num += 1
    output.write(row_num, 0, s.no)
    output.write(row_num, 1, s.id)
    output.write(row_num, 2, s.name)
    output.write(row_num, 3, s.surname)

output.save()
