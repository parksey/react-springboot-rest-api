import { useState } from "react";
import Header from "./Header";
import Main from "./Main";

function Index({ isLogin, setIsLogin }) {
  return (
    <>
      <Header isLogin={isLogin} setIsLogin={setIsLogin} />
      <Main isLogin={isLogin} setIsLogin={setIsLogin} />
    </>
  );
}

export default Index;
