import { useNavigate } from "react-router-dom";
import { OwnerRegistApi, RegistApi } from "../../api/UserApi";
import "../../style/Regist.css";

function OwnerRegist() {
  const navigate = useNavigate();
  const ownerPattern = /^\d{10}$/;
  const phonePattenr = /^\d{10,11}$/;

  const onSubmit = (e) => {
    e.preventDefault();

    if (!ownerPattern.test(e.target.ownerNo.value)) {
      alert("한국의 사업자 번호는 10자리 입니다.");
      return;
    }

    if (!phonePattenr.test(e.target.phone.value)) {
      alert("핸드폰 번호는 - 포함하지 않고 숫자만 입력해 주세요.");
      return;
    }

    OwnerRegistApi({
      ownerNo: e.target.ownerNo.value,
      email: e.target.email.value,
      phone: e.target.phone.value,
    }).then((response) => {
      console.log(response);

      if (response.success == true) {
        navigate("/");
      } else {
        alert("회원 가입 실패: " + response.message);
      }
    });
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
          <h1 className="h3 mb-3 font-weight-normal">사업자 등록</h1>

          <input
            type="text"
            id="ownerNo"
            className="form-control"
            placeholder="사업자 번호"
            required
            autoFocus
          />
          <input
            type="email"
            id="email"
            className="form-control"
            placeholder="이메일"
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

export default OwnerRegist;
