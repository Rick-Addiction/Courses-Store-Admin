export async function getAllUsers() {

    const response = await fetch('/online-courses/customer/search');
    return await response.json();
}