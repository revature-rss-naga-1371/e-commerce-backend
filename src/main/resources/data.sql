INSERT INTO product (id, quantity, price, description, image, name) VALUES (
    1,
    10,
    20.00,
    'A nice pair of headphones',
    'https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp',
    'Headphones'
),
(
    2,
    5,
    45.00,
    'A nice TeeShirt',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png',
    'TeeShirt'
),
(
    3,
    20,
    2.50,
    'A reusable shopping bag',
    'https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png',
    'Shopping Bag'
),
(
    4,
    20,
    10.00,
    'A fancy cap for a fancy person',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png',
    'Baseball Cap'
),
(
    5,
    3,
    80.00,
    'A nice coat',
    'https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png',
    'Coat'
);


INSERT INTO users (id, email, password, first_name, last_name) VALUES (
    1,
    'testuser@gmail.com',
    'password',
    'Test',
    'User'
);

INSERT INTO review (id, description, name, rating, product_id) VALUES (
    1,
    'Its the most awesome shirt.',
    'Its great!!!',
    4,
  2
),
(
  2,
  'You will never hear anything like it!',
  'THE BEST SOUND',
  5,
  1
),
(
  3,
  'It holds stuff, but not great.',
  'Its eh',
  3,
  3
),
(
  4,
  'This is any dog lovers must have hat.',
  'LOVE DOGS',
  5,
  4
),
(
  5,
  'its nice but we need more options, if there were more I would give two more stars.',
  'Need more options',
  2,
  5
);