from classes import *

student_list = StudentList('CES3063_Fall2020_rptSinifListesi.XLS')

Reports('zoom_poll_reports', student_list).read_reports()

results = Results(student_list.get_students())

Attendance(student_list.get_students(), results).add_attendance()

answer_keys = AnswerKeys('answer_keys').get_answer_keys()

CheckAnswers(student_list.get_students(), results, answer_keys).check()

results.save_book()
