import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { SpaceSave } from "../../api/spaces/SpacesApi";

function SpaceRegist({ isLogin, setIsLogin }) {
  const navigate = useNavigate();
  const onSubmit = (e) => {
    e.preventDefault();

    const parseStartAt = new Date(e.target.startAt.value).toISOString();
    const parseEndAt = new Date(e.target.endAt.value).toISOString();
    SpaceSave({
      title: e.target.title.value,
      location: e.target.location.value,
      description: e.target.description.value,
      capacity: e.target.capacity.value,
      amount: e.target.amount.value,
      startAt: parseStartAt,
      endAt: parseEndAt,
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
      <div className="Login-body">
        <link
          rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossOrigin="anonymous"
        />
        <form className="form-signin" id="form-login" onSubmit={onSubmit}>
          <h1 className="h3 mb-3 font-weight-normal">공유 장소 등록</h1>

          <input
            type="text"
            id="title"
            className="form-control"
            placeholder="공유 공간 이름"
            required
            autoFocus
          />
          <input
            type="text"
            id="location"
            className="form-control"
            placeholder="위치"
            required
            autoFocus
          />
          <input
            type="text"
            id="description"
            className="form-control"
            placeholder="내 공유 공간은? "
            required
            autoFocus
          />
          <input
            type="text"
            id="capacity"
            className="form-control"
            placeholder="최대 인원"
            required
            autoFocus
          />
          <input
            type="text"
            id="amount"
            className="form-control"
            placeholder="기간 내 가격"
            required
            autoFocus
          />
          <div>
            <label htmlFor="startDate">시작 기간:</label>
            <input
              type="datetime-local"
              id="startAt"
              className="form-control"
              placeholder="시작 기간"
              required
              autoFocus
            />
          </div>

          <div>
            <label htmlFor="endDate">종료 기간:</label>
            <input
              type="datetime-local"
              id="endAt"
              className="form-control"
              placeholder="종료 기간"
              required
            />
          </div>

          <button className="btn-login" type="submit">
            등록하러 가기
          </button>
          <div className="find"></div>
          <p className="mt-5 mb-3 text-muted">&copy; 2023</p>
        </form>
      </div>
    </>
  );
}

export default SpaceRegist;
