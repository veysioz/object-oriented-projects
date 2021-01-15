from classes import *

window = GUI()

button_list_student = Button(text="SELECT STUDENT LIST", command=window.student_list)
window.config_button_xls(button_list_student)

button_reports = Button(text="SELECT REPORTS FOLDER", command=window.reports)
window.config_button_folder(button_reports)

button_answers = Button(text="SELECT ANSWERS KEY FOLDER", command=window.answers)
window.config_button_folder(button_answers)

button_start = Button(text="START", command=window.start_process)
window.config_button_folder(button_start)

window.compile_window()
