export async function getAllTeachers() {

    const response = await fetch('/courses-store/teacher/search');
    console.log(JSON.stringify(response));

    return await response.json();
}

export async function createNewTeacher(newTeacher) {

    const response = await fetch('/courses-store/teacher/register', {
        method: "POST",
        body: JSON.stringify(newTeacher),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });

      console.log("new teacher", response);      
}

export async function editTeacher(teacherToEdit) {
    console.log(JSON.stringify(teacherToEdit));
    await fetch('/courses-store/teacher/update', {
        method: "PUT",
        body: JSON.stringify(teacherToEdit),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });
}

export async function deleteTeacher(idTeacherToDelete) {
    console.log(JSON.stringify(idTeacherToDelete));
    await fetch('/courses-store/teacher/' + idTeacherToDelete, {
        method: "DELETE"
      });
}