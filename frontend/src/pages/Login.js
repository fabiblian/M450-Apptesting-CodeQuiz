import { login} from "../services/authservice";
import LoginForm from "./Loginform";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();
  const [error, setError] = useState("");

  const handleLogin = async (loginData) => {
    setError("");
    
    try {
      // Direkt auth-service.js verwenden
      await login(loginData.usernameOrEmail, loginData.password);
      
      navigate("/quiz");
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-container">
        {error && <div className="error-message">❌ {error}</div>}
        
        {/* LoginForm bleibt unverändert! */}
        <LoginForm onLogin={handleLogin} />
        
        {/* Rest... */}
      </div>
    </div>
  );
};

export default Login;

