import React, { CSSProperties, useState } from "react";
import { RegistApi } from "../../api/UserApi";
import "../../style/Regist.css";
import { useNavigate } from "react-router-dom";

function UserRegist() {
  const [password, setPassword] = useState(""); // 비밀번호 일치확인
  const [confirmPassword, setConfirmPassword] = useState("");
  const [visible, setvisible] = useState(false);
  const [correctpassword, setcorrectpassword] = useState(false);
  const passwordType = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
  const onCancel = () => {
    setvisible(false);
  };

  const navigate = useNavigate();

  const onSubmit = (e) => {
    e.preventDefault();

    if (passwordType.test(e.target.password.value)) {
      if (password === confirmPassword) {
        setvisible(true);
        RegistApi({
          email: e.target.email.value,
          password: e.target.password.value,
          name: e.target.name.value,
          phone: e.target.phone.value,
        }).then((response) => {
          if (response.success == true) {
            navigate("/");
          } else {
            alert("회원 가입 실패: " + response.message);
          }
        });
      } else {
        alert("비밀번호가 일치하지 않습니다");
      }
    } else {
      alert("비밀번호 양식을 확인해주세요");
    }
  };

  const onChangePassword = (e) => {
    passwordType.test(e.target.value)
      ? setcorrectpassword(false)
      : setcorrectpassword(true);
    setPassword(e.target.value);
  };
  const onChangeConfirmPassword = (e) => {
    setConfirmPassword(e.target.value);
  };

  const uncorrectstyle = {
    border: password !== confirmPassword ? "4px solid var(--main-color)" : "",
  };

  const commentpassword = {
    display: correctpassword ? "block" : "none",
  };

  return (
    <>
      <div className="Login-body">
        <link
          rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossOrigin="anonymous"
        />
        <form className="form-signin" id="form-login" onSubmit={onSubmit}>
          <h1 className="h3 mb-3 font-weight-normal">회원가입 정보 입력</h1>

          <input
            type="email"
            id="email"
            className="form-control"
            placeholder="이메일 주소"
            required
            autoFocus
          />
          <input
            type="text"
            id="name"
            className="form-control"
            placeholder="이름"
            required
            autoFocus
          />
          <input
            type="text"
            id="phone"
            className="form-control"
            placeholder="핸드폰 번호"
            required
            autoFocus
          />
          <input
            type="password"
            id="password"
            className="form-control"
            placeholder="비밀번호"
            required
            autoFocus
            onChange={onChangePassword}
            style={uncorrectstyle}
          />
          <input
            type="password"
            id="password-check"
            className="form-control"
            placeholder="비밀번호 확인"
            required
            onChange={onChangeConfirmPassword}
            style={uncorrectstyle}
          />
          <div style={commentpassword}>
            비밀번호는 영어,숫자포함 8자에서 20자사이입니다
          </div>

          <button className="btn-login" type="submit">
            회원가입
          </button>
          <div className="find"></div>
          <p className="mt-5 mb-3 text-muted">&copy; 2023</p>
        </form>
      </div>
    </>
  );
}

export default UserRegist;
