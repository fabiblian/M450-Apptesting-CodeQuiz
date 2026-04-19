import { createContext, useContext, useState, useEffect, useMemo, useCallback } from 'react';
// Wichtig: Aliases (as ...) damit die Funktionen sich nicht selbst aufrufen!
import { login , logout , getUserData } from '../services/authservice';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(() => getUserData());
  const [token, setToken] = useState(() => localStorage.getItem('authToken'));
  const [isAuthenticated, setIsAuthenticated] = useState(() => !!localStorage.getItem('authToken'));
  const [isLoading, setIsLoading] = useState(true);

    const logout = useCallback(() => {
        logout();
    setToken(null);
    setUser(null);
    setIsAuthenticated(false);
  }, []);

  useEffect(() => {
    const checkAuth = async () => {
      try {
        const storedToken = localStorage.getItem('authToken');
        const userData = getUserData();

        if (storedToken && userData) {
          setToken(storedToken);
          setUser(userData);
          setIsAuthenticated(true);
        } else {
          logout();
        }
      } catch (error) {
        logout();
      } finally {
        setIsLoading(false);
      }
    };
    checkAuth();
  }, [logout]);

  const login = async (usernameOrEmail, password) => {
    try {
      // Nutzt apiLogin (den Import), nicht die lokale Funktion
      const response = await login(usernameOrEmail, password);
      setToken(response.token);
      setUser({
        id: response.userId,
        username: response.username,
        email: response.email,
        role: response.role
      });
      setIsAuthenticated(true);
      return response;
    } catch (error) {
      setIsAuthenticated(false);
      throw error;
    }
  };

  const logindatas = useMemo(() => ({
    user,
    token,
    isAuthenticated,
    isLoading,
    login,
    logout
  }), [user, token, isAuthenticated, isLoading, logout]);

  return (
    <AuthContext.Provider value={logindatas}>
      {children}
    </AuthContext.Provider >
  );
}; // <--- HIER muss die AuthProvider-Funktion enden!

// useAuth muss AUSSERHALB stehen
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth muss innerhalb von AuthProvider verwendet werden!');
  }
  return context;
};