import { Navigate ,useLocation,Outlet} from 'react-router-dom';
import {useContext} from 'react';
import AuthContext from '../store/auth-context';
function RequireAuth({ children, redirectTo }) {
  const ctx = useContext(AuthContext);
  let isAuthenticated = ctx.isLoggedIn;
  let location = useLocation();
  

  if (!isAuthenticated) {
    // Redirect them to the /login page, but save the current location they were
    // trying to go to when they were redirected. This allows us to send them
    // along to that page after they login, which is a nicer user experience
    // than dropping them off on the home page.
    return <Navigate to="/login" state={{ from: location }} />;
  }

  return <Outlet />;
}

  export default RequireAuth;
  