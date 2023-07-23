import React, { useState, useEffect } from "react";
import { Link, Routes, Route } from "react-router-dom";

import "./App.css";
import "./style/Index.css";

import Index from "./component/Index";
import Login from "./component/login/Login";
import ShareSpace from "./component/space/Spaces";
import Space from "./component/space/Space";
import SpaceRegist from "./component/space/SpaceRegist";
import SelectLogin from "./component/regist/SelectRegist";
import UserRegist from "./component/regist/UserRegist";
import OwnerRegist from "./component/regist/OwnerRegist";
import OwnerLogin from "./component/login/OwnerLogin";
import Myspace from "./component/space/Myspace";
import Myspaces from "./component/space/Myspaces";
import Myregist from "./component/Myregist";

function App() {
  const [isLogin, setIsLogin] = useState(false);

  return (
    <div className="ALl">
      <Routes>
        <Route
          path="/"
          element={<Index isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>

        <Route path="/owner/login" element={<OwnerLogin />}></Route>
        <Route path="/user/login" element={<Login />}></Route>
        <Route path="/login/select" element={<SelectLogin />}></Route>
        <Route path="/user/regist" element={<UserRegist />}></Route>
        <Route path="/owner/regist" element={<OwnerRegist />}></Route>

        <Route
          path="/sharespace"
          element={<ShareSpace isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>
        <Route
          path="/sharespace/space"
          element={<ShareSpace isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>
        <Route
          path="/sharespace/space/:spaceId"
          element={<Space isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>

        <Route
          path="/space/regist"
          element={<SpaceRegist isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>

        <Route
          path="/myspace"
          element={<Myspaces isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>
        <Route
          path="/myspace/space"
          element={<Myspaces isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>
        <Route
          path="/myspace/space/:spaceId"
          element={<Myspace isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>

        <Route
          path="/myregist"
          element={<Myregist isLogin={isLogin} setIsLogin={setIsLogin} />}
        ></Route>
      </Routes>
    </div>
  );
}

export default App;
