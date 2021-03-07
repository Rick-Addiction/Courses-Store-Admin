export async function getAllUsers() {

    const response = await fetch('/courses-store/customer/search');
        
    return await response.json();
}