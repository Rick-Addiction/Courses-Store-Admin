export async function getAllCustomers() {

    const response = await fetch('/courses-store/customer/search');
    console.log(JSON.stringify(response));

    return await response.json();
}

export async function getAcquiredCoursesByCustomer(idCustomer) {

    const response = await fetch('/courses-store/customer/' + idCustomer + '/acquired-courses');
    console.log(JSON.stringify(response));
    return await response.json();
}

export async function getDesiredCoursesByCustomer(idCustomer) {

    const response = await fetch('/courses-store/customer/' + idCustomer + '/desired-courses');
    console.log(JSON.stringify(response));
    return await response.json();
}

export async function createNewCustomer(newCustomer) {

    console.log("new customer", newCustomer);
    const response = await fetch('/courses-store/customer/register', {
        method: "POST",
        body: JSON.stringify(newCustomer),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });      
}

export async function AddAcquiredCoursesByCustomer(idCustomer,newAcquisition) {

    console.log("new Acquisition", newAcquisition);
    const response = await fetch('/courses-store/customer/' + idCustomer + '/acquire-course', {
        method: "POST",
        body: JSON.stringify(newAcquisition),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });
}

export async function AddDesiredCoursesByCustomer(idCustomer,newDesire) {

    console.log("new Desire", newDesire);
    const response = await fetch('/courses-store/customer/' + idCustomer + '/desire-course', {
        method: "POST",
        body: JSON.stringify(newDesire),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });
}

export async function editCustomer(customerToEdit) {
    console.log(JSON.stringify(customerToEdit));
    await fetch('/courses-store/customer/update', {
        method: "PUT",
        body: JSON.stringify(customerToEdit),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });
}

export async function deleteCustomer(idCustomerToDelete) {
    console.log(JSON.stringify(idCustomerToDelete));
    await fetch('/courses-store/customer/' + idCustomerToDelete, {
        method: "DELETE"
      });
}