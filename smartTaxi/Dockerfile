# Use a base image with Node 14
FROM node:14

WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH

# Copy package.json and package-lock.json files for npm install
COPY package*.json ./

# Install npm dependencies
RUN npm install

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Copy the entire Angular application
COPY . .

# Build the Angular app
RUN ng build

# Prune unnecessary npm dependencies for production
RUN npm prune --production

# Expose the port that your Angular application will run on
EXPOSE 4200

# Set the command to serve the Angular app
CMD ["ng", "serve", "--host", "0.0.0.0"]
