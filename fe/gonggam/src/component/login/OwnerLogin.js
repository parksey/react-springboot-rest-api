import { LoginApi, OwnerLoginApi } from "../../api/UserApi";
import { Link, useNavigate } from "react-router-dom";

import "../../style/Login.css";

function OwnerLogin() {
  const navigate = useNavigate();
  const onSubmit = (e) => {
    e.preventDefault();
    console.log("??");
    OwnerLoginApi({
      ownerNo: e.target.ownerNo.value,
    }).then((response) => {
      console.log(response);
      if (response.success == true) {
        navigate("/");
      } else {
        alert("로그인 실패: " + response.message);
      }
    });
  };

  return (
    <>
      <div className="MkProject">
        <div className="Login-body">
          <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossOrigin="anonymous"
          />
          <form className="form-signin" id="form-login" onSubmit={onSubmit}>
            <h1 className="h3 mb-3 font-weight-normal">사업자 로그인</h1>
            <label htmlFor="inputOwnerNo" className="sr-only">
              사업자 번호
            </label>
            <input
              type="text"
              id="ownerNo"
              className="form-control"
              placeholder="사업자 번호를 입력하세요"
              required
              autoFocus
            />

            <button className="btn-login" type="submit">
              로그인
            </button>

            <div className="find">
              <Link to="/owner/regist">회원가입</Link>
            </div>
            <p className="mt-5 mb-3 text-muted">&copy; 2023</p>
          </form>
        </div>
      </div>
    </>
  );
}

export default OwnerLogin;
