import type { NextPage } from 'next';

import { Button } from '@g.organizer/components';

const Home: NextPage = () => {
  return <Button content="Hello dark friend 2" onClick={() => alert(10)} />;
};

export default Home;
