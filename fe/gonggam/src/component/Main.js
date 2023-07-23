import { Link, useLocation } from "react-router-dom";

import "../style/Main.css";
import { ValidSessionCheck } from "../api/UserApi";

function Main({ isLogin, setIsLogin }) {
  const useCheckSession = async (e) => {
    const result = await ValidSessionCheck();
    setIsLogin(result);
    console.log(isLogin, result);
  };

  return (
    <>
      <div className="main-body">
        <div className="description">
          <p>함께 배우고,</p>
          <br />
          <p>함께 경험하고</p>
          <br />
          <br />
          <p>취미를 함께하다.</p>

          <div className="project">
            <Link
              to={isLogin ? "/space/regist" : "/login/select"}
              className="indexbtn"
              onClick={useCheckSession}
            >
              장소 등록
            </Link>
            <Link
              to={isLogin ? "/sharespace" : "/login/select"}
              className="indexbtn"
              onClick={useCheckSession}
            >
              장소 대여
            </Link>
            <Link
              to={isLogin ? "/" : "/login/select"}
              className="indexbtn"
              onClick={useCheckSession}
            >
              취미 찾기
            </Link>
            <Link
              to={isLogin ? "/" : "/login/select"}
              className="indexbtn"
              onClick={useCheckSession}
            >
              배우기
            </Link>
          </div>
        </div>
      </div>
      <div className="main-body temp">
        <div className="description">
          <p>추후 변경예정</p>
          <div className="project">
            <Link
              to={isLogin ? "/myregist" : "/login/select"}
              className="indexbtn"
              onClick={useCheckSession}
            >
              내 예약 보기
            </Link>
            <Link
              to={isLogin ? "/myspace" : "/login/select"}
              className="indexbtn"
              onClick={useCheckSession}
            >
              내 공유 장소 보기
            </Link>
          </div>
        </div>
      </div>
    </>
  );
}

export default Main;
