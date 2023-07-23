import { Link } from "react-router-dom";

import "../../style/Spaces.css";
import "../../style/SelectRegist.css";

function SelectLogin() {
  return (
    <>
      <div className="component-container up">
        <Link
          to={`/owner/login`}
          className="component-inner-box Make-component-box"
        >
          <div className="inner-text">사업자 회원 가입하기</div>
        </Link>
        <Link
          to={`/user/login`}
          className="component-inner-box Make-component-box"
        >
          <div className="inner-text">사용자 회원 가입하기</div>
        </Link>
      </div>
    </>
  );
}

export default SelectLogin;
