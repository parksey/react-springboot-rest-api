import { useEffect, useState } from "react";
import "../style/Spaces.css";
import Header from "./Header";
import { Link } from "react-router-dom";
import { SpaceSummary } from "./space/Spaces";
import { MyReserveApi } from "../api/spaces/SpacesApi";

function ReserveInfo({ reserveId, reserve }) {
  return (
    <>
      <div className="info">
        <div className="main-info">
          <div>{reserve.title}</div>
          <div>예약 상태: {reserve.reservationStatus}</div>
        </div>
        <div className="sub-info">
          <div>위치: {reserve.location}</div>
          <div>금액: {reserve.amount}</div>
          <div>예약 신청시간: {reserve.createAt}</div>
        </div>
      </div>
    </>
  );
}

function Myregist({ isLogin, setIsLogin }) {
  const [reserves, setReserves] = useState([]);

  console.log(reserves);
  useEffect(() => {
    const fetchData = async () => {
      const reserveList = await MyReserveApi();
      setReserves(reserveList);
    };
    fetchData();
  }, []);

  return (
    <>
      <Header isLogin={isLogin} setIsLogin={setIsLogin} />
      <div className="component-container">
        {reserves?.map((reserve, index) => {
          return (
            <Link
              to={`/sharespace/space/${reserve.spaceId}`}
              className="component-inner-box Make-component-box"
            >
              <ReserveInfo key={index} reserve={reserve} />
            </Link>
          );
        })}
      </div>
    </>
  );
}

export default Myregist;
