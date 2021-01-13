from classes import *

student_list = StudentList('CES3063_Fall2020_rptSinifListesi.XLS')

Reports('zoom_poll_reports', student_list).read_reports()

results = Results(student_list.get_students())

Attendance(student_list.get_students(), results).add_attendance()

answer_keys = []
#answer_keys = AnswerKeys('answer_keys').get_answer_keys()
for answer_key in answer_keys:
    print(answer_key.get_poll_name(), ":")
    for question in answer_key:
        print(question.get_question(), question.get_answer())

results.save_book()
