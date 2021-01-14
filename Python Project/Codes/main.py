from classes import *

test= GUI()

button = Button( text="SELECT CLASS LIST", command=test.open_dialog)
test.config_button(button)


button2 = Button( text="LIST STUDENT ", command=test.StudentList)
test.config_button(button2)
'''
button3 = Button( text="button", command=test.open_dialog)
test.config_button(button3)
'''


test.compileWindow()



#student_list = StudentList('CES3063_Fall2020_rptSinifListesi.XLS') # exceli okur

#Reports('zoom_poll_reports', student_list).read_reports() #poll bilgisi ni student list e ekliyor

#results = Results(student_list.get_students())

#Attendance(student_list.get_students(), results).add_attendance()

#answer_keys = AnswerKeys('answer_keys').get_answer_keys()

#CheckAnswers(student_list.get_students(), results, answer_keys).check()

#results.save_book()
