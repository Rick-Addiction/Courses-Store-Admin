export async function getAllCustomers() {

    const response = await fetch('/courses-store/customer/search');
        
    return await response.json();
}

export async function createNewCustomer(newCustomer) {

    await fetch('/courses-store/customer/register', {
        method: "POST",
        body: JSON.stringify(newCustomer),
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