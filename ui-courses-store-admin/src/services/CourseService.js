export async function getAllCourses() {

    const response = await fetch('/courses-store/course/search');
    console.log(JSON.stringify(response));

    return await response.json();
}

export async function createNewCourse(newCourse) {

    var newCoursePayload= {
        name: newCourse.name,
        original_value: newCourse.originalValue,
        id_teacher_responsible: newCourse.idTeacher
      }

    const response = await fetch('/courses-store/course/register', {
        method: "POST",
        body: JSON.stringify(newCoursePayload),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });

      console.log("new course", response);
}

export async function editCourse(courseToEdit) {
    console.log("courseToEdit",courseToEdit);

    var editCoursePayload= {
        id_course: courseToEdit.idCourse,
        name: courseToEdit.name,
        original_value: courseToEdit.originalValue,
        id_teacher_responsible: courseToEdit.idTeacher
      }


    console.log(JSON.stringify(editCoursePayload));
    await fetch('/courses-store/course/update', {
        method: "PUT",
        body: JSON.stringify(editCoursePayload),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });
}

export async function deleteCourse(idCourseToDelete) {
    console.log(JSON.stringify(idCourseToDelete));
    await fetch('/courses-store/course/' + idCourseToDelete, {
        method: "DELETE"
      });
}