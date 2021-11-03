export function calculateLineDistance (start, end) {
  const d1 = 0.01745329251994329;
  let d2 = start.longitude;
  let d3 = start.latitude;
  let d4 = end.longitude;
  let d5 = end.latitude;
  d2 *= d1;
  d3 *= d1;
  d4 *= d1;
  d5 *= d1;
  const d6 = Math.sin(d2);
  const d7 = Math.sin(d3);
  const d8 = Math.cos(d2);
  const d9 = Math.cos(d3);
  const d10 = Math.sin(d4);
  const d11 = Math.sin(d5);
  const d12 = Math.cos(d4);
  const d13 = Math.cos(d5);
  const arrayOfDouble1 = [];
  const arrayOfDouble2 = [];
  arrayOfDouble1.push(d9 * d8);
  arrayOfDouble1.push(d9 * d6);
  arrayOfDouble1.push(d7);
  arrayOfDouble2.push(d13 * d12);
  arrayOfDouble2.push(d13 * d10);
  arrayOfDouble2.push(d11);
  const d14 = Math.sqrt((arrayOfDouble1[0] - arrayOfDouble2[0]) * (arrayOfDouble1[0] - arrayOfDouble2[0]) +
                    (arrayOfDouble1[1] - arrayOfDouble2[1]) * (arrayOfDouble1[1] - arrayOfDouble2[1]) +
                    (arrayOfDouble1[2] - arrayOfDouble2[2]) * (arrayOfDouble1[2] - arrayOfDouble2[2]));

  return (Math.asin(d14 / 2.0) * 12742001.579854401);
}
