import { Link, useLocation, useNavigate } from "react-router-dom";

import "../style/Header.css";
import { useCookies } from "react-cookie";
import { useEffect, useState } from "react";
import { ValidLogin, ValidSessionCheck } from "../api/UserApi";

function Header({ isLogin, setIsLogin }) {
  const [cookies, setCookie, removeCookie] = useCookies(["JSESSIONID"]);
  const navigate = useNavigate();
  const location = useLocation();

  const onlogout = () => {
    console.log("LOGOUT: ", isLogin);
    if (isLogin) {
      removeCookie("JSESSIONID", { path: "/", domain: "localhost" });
      setIsLogin(false);
      navigate(location.pathname);
    }
  };

  useEffect(() => {
    const checkSession = async () => {
      const result = await ValidLogin();
      setIsLogin(result);
    };
    checkSession();
  }, []);

  return (
    <>
      <header className="Header">
        <Link to="/" className="left">
          <div className="logo">공감</div>
        </Link>
        <Link
          to={isLogin ? "/" : "/login/select"}
          onClick={onlogout}
          className="right"
        >
          {isLogin ? "로그아웃" : "로그인"}
        </Link>
      </header>
    </>
  );
}

export default Header;
