# Use Node.js version 18.17.0 from the Alpine image
FROM node:18.17.0-alpine

# Set the working directory in the Docker container
WORKDIR /app

# Copy package.json and package-lock.json (or yarn.lock) to use Docker caching
COPY package*.json ./
# If you are using yarn, uncomment the next line
# COPY yarn.lock ./

# Install dependencies
RUN npm install
# For yarn, use the following command
# RUN yarn install

# Copy the rest of your application code
COPY . .

# Build your Next.js application
RUN npm run build

# Inform Docker that the container listens on port 3000 at runtime
EXPOSE 3000

# Define the command to run your app (customize this according to your start script)
CMD ["npm", "start"]
