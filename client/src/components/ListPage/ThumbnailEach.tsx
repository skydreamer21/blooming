import { ArtistInfo } from '@type/Artist';
import React from 'react';
import styled from 'styled-components';
import { calculateDateDifference } from './RankBox';
import { Bar, Progress, ProgressBar } from '@style/common';

interface Props {
  data: ArtistInfo;
}
const ThumbnailEach: React.FC<Props> = ({ data }) => {
  return (
    <EachFrame>
      <img src={data.profile_img}></img>
      <Info>
        <div className="txtInfo">
          <div className="name">{data.name}</div>
          <div className="leftDate">
            {calculateDateDifference(new Date().toString(), data.endDate)} 일
            남음
          </div>
        </div>
        <ProgressBar>
          <Progress score={data.nowNft} total={data.totalNft}>
            <Bar></Bar>
          </Progress>
        </ProgressBar>
      </Info>
    </EachFrame>
  );
};

const EachFrame = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 40px;
  cursor: pointer;
  img {
    width: 300px;
    height: 250px;
    border-radius: 6px;
  }
`;
const Info = styled.div`
  margin-top: 16px;

  .txtInfo {
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 18px;
    font-weight: 700;
    line-height: 25px; /* 125% */

    .leftDate {
      color: var(--main1-color);
      font-size: 14px;
      font-weight: 700;
      line-height: 25px; /* 178.571% */
    }
  }
`;
export default ThumbnailEach;
