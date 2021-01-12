from classes import *

student_list = StudentList('CES3063_Fall2020_rptSinifListesi.XLS')

reports = Reports('zoom_poll_reports', student_list)
reports.read_reports()

results = Results(student_list.get_students())

attendance = Attendance(student_list.get_students(), results)
attendance.add_attendance()

results.save_book()
