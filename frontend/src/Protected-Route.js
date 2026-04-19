// Protected-Route.js
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from './contexts/AuthContext';

const ProtectedRoute = () => {
  const { isAuthenticated, isLoading } = useAuth();

  // Falls der Auth-Check noch läuft, zeigen wir nichts oder einen Spinner
  // (Verhindert kurzes Aufblitzen der Login-Seite beim Refresh)
  if (isLoading) {
      return <p>Appliaktion ladet...</p>
      
  }

  // Wenn nicht eingeloggt, redirect zu Home oder Login
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  // Wenn eingeloggt, rendere die Kind-Elemente (Outlet)
  return <Outlet />;
};

export default ProtectedRoute;