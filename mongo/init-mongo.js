const database = db.getSiblingDB('cosmetics');

function ensureCollection(name, schema) {
  if (!database.getCollectionNames().includes(name)) {
    database.createCollection(name, { validator: { $jsonSchema: schema } });
    print('Collection créée : ' + name);
  } else {
    print('Collection déjà présente : ' + name);
  }
}

ensureCollection('categories', {
  bsonType: 'object', required: ['name'],
  properties: { name: { bsonType: 'string' }, description: { bsonType: 'string' } }
});
ensureCollection('products', {
  bsonType: 'object', required: ['name', 'price', 'stock', 'categoryId'],
  properties: {
    name: { bsonType: 'string' }, description: { bsonType: 'string' },
    price: { bsonType: 'number', minimum: 0 }, stock: { bsonType: 'number', minimum: 0 },
    categoryId: { bsonType: 'string' }
  }
});
ensureCollection('customers', {
  bsonType: 'object', required: ['firstName', 'lastName', 'email'],
  properties: {
    firstName: { bsonType: 'string' }, lastName: { bsonType: 'string' },
    email: { bsonType: 'string' }, phone: { bsonType: 'string' }
  }
});
ensureCollection('orders', {
  bsonType: 'object', required: ['orderDate', 'status', 'customerId'],
  properties: {
    orderDate: { bsonType: 'date' }, status: { bsonType: 'string' },
    total: { bsonType: 'number' }, customerId: { bsonType: 'string' }
  }
});

database.categories.createIndex({ name: 1 }, { unique: true });
database.customers.createIndex({ email: 1 }, { unique: true });
database.products.createIndex({ categoryId: 1 });
database.orders.createIndex({ customerId: 1 });

if (database.categories.countDocuments() === 0) {
  const c = database.categories.insertMany([
    { name: 'Soins du visage', description: 'Crèmes, sérums, nettoyants' },
    { name: 'Maquillage', description: 'Teint, yeux, lèvres' },
    { name: 'Soins du corps', description: 'Hydratants, gommages' },
    { name: 'Parfums', description: 'Eaux de parfum' }
  ]).insertedIds;
  const id = (i) => c[i].toString();

  database.products.insertMany([
    { name: 'Sérum hydratant', description: 'Acide hyaluronique', price: 24.9, stock: 40, categoryId: id(0) },
    { name: 'Crème anti-rides', description: 'Rétinol doux', price: 32.5, stock: 18, categoryId: id(0) },
    { name: 'Rouge à lèvres rose', description: 'Tenue 12 h', price: 14.5, stock: 25, categoryId: id(1) },
    { name: 'Mascara volume', description: 'Waterproof', price: 12.9, stock: 3, categoryId: id(1) },
    { name: 'Lait corps karité', description: 'Peaux sèches', price: 11.0, stock: 30, categoryId: id(2) },
    { name: 'Eau de parfum Rose', description: '50 ml', price: 49.9, stock: 12, categoryId: id(3) }
  ]);

  const cu = database.customers.insertMany([
    { firstName: 'Sarra', lastName: 'Ben Ali', email: 'sarra@example.com', phone: '20123456' },
    { firstName: 'Mehdi', lastName: 'Trabelsi', email: 'mehdi@example.com', phone: '98123456' },
    { firstName: 'Lina', lastName: 'Gharbi', email: 'lina@example.com' }
  ]).insertedIds;

  database.orders.insertMany([
    { orderDate: new Date(), status: 'PENDING', total: 39.4, customerId: cu[0].toString() },
    { orderDate: new Date(), status: 'DELIVERED', total: 49.9, customerId: cu[1].toString() },
    { orderDate: new Date(), status: 'SHIPPED', total: 11.0, customerId: cu[2].toString() }
  ]);
  print('Données de démonstration insérées');
}
printjson(database.getCollectionNames());
