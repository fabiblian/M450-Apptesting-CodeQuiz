


export const logout = () => {
  console.log('🚪 Logout - Daten werden gelöscht');
  localStorage.removeItem('authToken');
  localStorage.removeItem('userData');
};

/**
 * Hole User-Daten aus localStorage
 * (Brauchen keinen Backend-Call, haben alles vom Login!)
 */
export const getUserData = () => {
  const userDataString = localStorage.getItem('userData');
  if (userDataString) {
    return JSON.parse(userDataString);
  }
  return null;
};


export const login = async (credentials) => {
  // 1. Logic to call your API (e.g., fetch or axios)
  // 2. On success, save the token and user data:
  //    localStorage.setItem('authToken', response.token);
  //    localStorage.setItem('userData', JSON.stringify(response.user));
  
  console.log('🔑 Login attempt with:', credentials);
  
  // Example placeholder logic:
  return { success: true }; 
};